package com.api.pathwise.service;

import com.api.pathwise.dto.NodeType;
import com.api.pathwise.dto.enumpath.PathStep;
import com.api.pathwise.entity.EnumeratedPath;
import com.api.pathwise.entity.Flow;
import com.api.pathwise.entity.FlowEdge;
import com.api.pathwise.entity.FlowNode;
import com.api.pathwise.exception.ResourceNotFoundException;
import com.api.pathwise.repository.EnumeratedPathRepository;
import com.api.pathwise.repository.FlowEdgeRepository;
import com.api.pathwise.repository.FlowNodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PathEnumeratorService {
    private final FlowService flowService;
    private final FlowNodeRepository flowNodeRepository;
    private final FlowEdgeRepository flowEdgeRepository;
    private final EnumeratedPathRepository enumeratedPathRepository;

    @Transactional
    public List<EnumeratedPath> enumerateAndSave(Long flowId) {
        Flow flow = flowService.getFlow(flowId);

        List<FlowNode> nodes = flowNodeRepository.findByFlow_Id(flowId);
        List<FlowEdge> edges = flowEdgeRepository.findByFlow_Id(flowId);

        Map<Long, List<FlowEdge>> adjacency = edges.stream()
                .collect(Collectors.groupingBy(e -> e.getFromNode().getId()));

        List<FlowNode> startNodes = nodes.stream()
                .filter(n -> n.getNodeType() == NodeType.start)
                .toList();

        if (startNodes.isEmpty()) {
            throw new ResourceNotFoundException("Flow has no start node: " + flowId);
        }

        List<List<PathStep>> allPaths = new ArrayList<>();

        for (FlowNode start : startNodes) {
            List<PathStep> currentPath = new ArrayList<>();
            Set<Long> visited = new HashSet<>();
            traverse(start, null, adjacency, currentPath, visited, allPaths);
        }

        List<EnumeratedPath> saved = new ArrayList<>();
        for (List<PathStep> path : allPaths) {
            EnumeratedPath ep = new EnumeratedPath();
            ep.setFlow(flow);
            ep.setPathSequence(path);
            saved.add(enumeratedPathRepository.save(ep));
        }

        return saved;
    }

    private void traverse(FlowNode current, String conditionTaken,
                          Map<Long, List<FlowEdge>> adjacency,
                          List<PathStep> currentPath, Set<Long> visited,
                          List<List<PathStep>> allPaths) {

        currentPath.add(new PathStep(current.getId(), current.getNodeKey(), conditionTaken));
        visited.add(current.getId());

        if (current.getNodeType() == NodeType.end) {
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            List<FlowEdge> outgoing = adjacency.getOrDefault(current.getId(), List.of());
            for (FlowEdge edge : outgoing) {
                FlowNode next = edge.getToNode();
                if (!visited.contains(next.getId())) {
                    traverse(next, edge.getConditionLabel(), adjacency, currentPath, visited, allPaths);
                }
            }
        }

        // backtrack so the next branch starts clean
        currentPath.removeLast();
        visited.remove(current.getId());
    }

}
