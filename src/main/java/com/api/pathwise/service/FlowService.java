package com.api.pathwise.service;

import com.api.pathwise.dto.flow.CreateFlowDto;
import com.api.pathwise.dto.flow.FlowDto;
import com.api.pathwise.dto.flow.UpdateFlowDto;
import com.api.pathwise.entity.Flow;
import com.api.pathwise.exception.ResourceNotFoundException;
import com.api.pathwise.mapper.FlowMapper;
import com.api.pathwise.repository.FlowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FlowService {

    private final FlowRepository flowRepository;
    private final FlowMapper flowMapper;

    public Page<FlowDto> getAll(String searchTerm, Pageable pageable) {
        return flowRepository.findAllWithPagination(searchTerm, pageable)
                .map(flowMapper::toSimpleDto);
    }

    public FlowDto getById(Long id) {
        Flow flowFromDb = getFlow(id);
        return flowMapper.toSimpleDto(flowFromDb) ;
    }



    public FlowDto create(CreateFlowDto request) {

        Flow flow = new Flow();
        flow.setName(request.getName());
        flow.setDescription(request.getDescription());
        flowRepository.save(flow);

        return flowMapper.toSimpleDto(flow);
    }

    public FlowDto update(Long id, UpdateFlowDto request) {
        Flow flowFromDb = getFlow(id);

        flowFromDb.setName(request.getName());
        flowFromDb.setDescription(request.getDescription());
        flowRepository.save(flowFromDb);

        return flowMapper.toSimpleDto(flowFromDb);
    }


    // Get Flow from DB by its ID

    public @NonNull Flow getFlow(Long id) {
        return flowRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id));
    }

}
