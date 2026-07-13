package com.api.pathwise.service;

import com.api.pathwise.dto.flowedge.CreateFlowEdgeDto;
import com.api.pathwise.dto.flowedge.FlowEdgeDto;
import com.api.pathwise.dto.flowedge.UpdateFlowEdgeDto;
import com.api.pathwise.dto.flownode.FlowNodeDto;
import com.api.pathwise.dto.flownode.UpdateFlowNodeDto;
import com.api.pathwise.entity.Flow;
import com.api.pathwise.entity.FlowEdge;
import com.api.pathwise.entity.FlowNode;
import com.api.pathwise.exception.DuplicateResourceException;
import com.api.pathwise.mapper.FlowEdgeMapper;
import com.api.pathwise.repository.FlowEdgeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FlowEdgeService {
    private final FlowEdgeRepository flowEdgeRepository;
    private final FlowEdgeMapper flowEdgeMapper;
    private final FlowService flowService;
    private final FlowNodeService flowNodeService;

    public Page<FlowEdgeDto> getAll(Pageable pageable) {
        return flowEdgeRepository.findAll(pageable)
                .map(flowEdgeMapper::toSimpleDto);
    }

    public FlowEdgeDto getById(Long id) {
        FlowEdge flowEdge = getFlowEdge(id);
        return flowEdgeMapper.toSimpleDto((flowEdge));
    }

    public FlowEdgeDto create(CreateFlowEdgeDto request) {
        Flow flowRef = flowService.getFlow(request.getFlowId());
        FlowNode fromFlowNodeRef = flowNodeService.getFlowNodeById(request.getFromNodeId());
        FlowNode toFlowNodeRef = flowNodeService.getFlowNodeById(request.getToNodeId());

        FlowEdge flowEdge = new FlowEdge();
        flowEdge.setFlow(flowRef);
        flowEdge.setFromNode(fromFlowNodeRef);
        flowEdge.setToNode(toFlowNodeRef);
        flowEdge.setConditionLabel(request.getConditionLabel());
        flowEdgeRepository.save(flowEdge);

        return flowEdgeMapper.toSimpleDto(flowEdge);
    }

    public FlowEdgeDto update(Long id, UpdateFlowEdgeDto request) {
        FlowEdge flowEdge = getFlowEdge(id);

        Flow flowRef = flowService.getFlow(request.getFlowId());
        FlowNode fromFlowNodeRef = flowNodeService.getFlowNodeById(request.getFromNodeId());
        FlowNode toFlowNodeRef = flowNodeService.getFlowNodeById(request.getToNodeId());

        flowEdge.setFlow(flowRef);
        flowEdge.setFromNode(fromFlowNodeRef);
        flowEdge.setToNode(toFlowNodeRef);
        flowEdge.setConditionLabel(request.getConditionLabel());
        flowEdgeRepository.save(flowEdge);

        return flowEdgeMapper.toSimpleDto(flowEdge);
    }


    private @NonNull FlowEdge getFlowEdge(Long id) {
        return flowEdgeRepository.findById(id)
                .orElseThrow(() -> new DuplicateResourceException("Resource not found with id: " + id));
    }
}
