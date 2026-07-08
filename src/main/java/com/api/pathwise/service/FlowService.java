package com.api.pathwise.service;

import com.api.pathwise.dto.flows.CreateFlowRequest;
import com.api.pathwise.dto.flows.FlowResponse;
import com.api.pathwise.dto.flows.UpdateFlowRequest;
import com.api.pathwise.entity.Flow;
import com.api.pathwise.exception.ResourceNotFoundException;
import com.api.pathwise.mapper.FlowMapper;
import com.api.pathwise.repository.FlowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FlowService {

    private final FlowRepository flowRepository;
    private final FlowMapper flowMapper;

    public Page<FlowResponse> getAll(String searchTerm, Pageable pageable) {
        return flowRepository.findAllWithPagination(searchTerm, pageable)
                .map(flowMapper::toSimpleDto);
    }

    public FlowResponse getById(Long id) {
        Flow flowFromDb = flowRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id));
        return flowMapper.toSimpleDto(flowFromDb) ;
    }

    public FlowResponse create(CreateFlowRequest request) {

        Flow flow = new Flow();
        flow.setName(request.getName());
        flow.setDescription(request.getDescription());
        flowRepository.save(flow);

        return flowMapper.toSimpleDto(flow);
    }

    public FlowResponse update(Long id, UpdateFlowRequest request) {
        Flow flowFromDb = flowRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flow not found with id: " + id));

        flowFromDb.setName(request.getName());
        flowFromDb.setDescription(request.getDescription());
        flowRepository.save(flowFromDb);

        return flowMapper.toSimpleDto(flowFromDb);
    }



}
