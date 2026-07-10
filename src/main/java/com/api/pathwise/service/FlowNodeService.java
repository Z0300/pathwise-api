package com.api.pathwise.service;

import com.api.pathwise.dto.flownode.CreateFlowNodeDto;
import com.api.pathwise.dto.flownode.FlowNodeDto;
import com.api.pathwise.dto.flownode.UpdateFlowNodeDto;
import com.api.pathwise.entity.Flow;
import com.api.pathwise.entity.FlowNode;
import com.api.pathwise.exception.ResourceNotFoundException;
import com.api.pathwise.mapper.FlowNodeMapper;
import com.api.pathwise.repository.FlowNodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FlowNodeService {
    private final FlowNodeRepository flowNodeRepository;
    private final FlowNodeMapper flowNodeMapper;
    private final FlowService flowService;


    public Page<FlowNodeDto> getAll(Pageable pageable) {
        return flowNodeRepository.findAll(pageable)
                .map(flowNodeMapper::toSimpleDto);
    }

    public FlowNodeDto getById(Long id) {
        FlowNode flowNode = getFlowNodeById(id);
        return flowNodeMapper.toSimpleDto((flowNode));
    }


    public FlowNodeDto create(CreateFlowNodeDto request) {

        Flow flowRef = flowService.getFlowFromDb(request.getFlowId());

        FlowNode flowNode = new FlowNode();
        flowNode.setFlow(flowRef);
        flowNode.setNodeKey(request.getNodeKey());
        flowNode.setNodeType(request.getNodeType());
        flowNode.setLabel(request.getLabel());
        flowNodeRepository.save(flowNode);

        return flowNodeMapper.toSimpleDto(flowNode);
    }

    public FlowNodeDto update(Long id, UpdateFlowNodeDto request) {
        FlowNode flowNode = getFlowNodeById(id);

        Flow flowRef = flowService.getFlowFromDb(request.getFlowId());

        flowNode.setFlow(flowRef);
        flowNode.setNodeKey(request.getNodeKey());
        flowNode.setNodeType(request.getNodeType());
        flowNode.setLabel(request.getLabel());
        flowNodeRepository.save(flowNode);

        return flowNodeMapper.toSimpleDto(flowNode);
    }

    private @NonNull FlowNode getFlowNodeById(Long id) {
        return flowNodeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id));
    }
}
