package com.api.pathwise.service;

import com.api.pathwise.dto.enumpath.CreateEnumPathDto;
import com.api.pathwise.dto.enumpath.EnumPathDto;
import com.api.pathwise.dto.enumpath.UpdateEnumPathDto;
import com.api.pathwise.entity.EnumeratedPath;
import com.api.pathwise.entity.Flow;
import com.api.pathwise.exception.ResourceNotFoundException;
import com.api.pathwise.mapper.EnumeratedPathMapper;
import com.api.pathwise.repository.EnumeratedPathRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnumeratedPathService {
    private final EnumeratedPathRepository enumeratedPathRepository;
    private final EnumeratedPathMapper enumeratedPathMapper;
    private final FlowService flowService;


    public Page<EnumPathDto> getAll(Pageable pageable) {
        return enumeratedPathRepository.findAll(pageable)
                .map(enumeratedPathMapper::toSimpleDto);
    }

    public EnumPathDto getById(Long id) {
        EnumeratedPath pathFromDb = enumeratedPathRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id));

        return enumeratedPathMapper.toSimpleDto(pathFromDb);
    }

    public EnumPathDto create(CreateEnumPathDto request) {

        Flow flowRef = flowService.getFlowFromDb(request.getFlowId());

        EnumeratedPath enumeratedPath = new EnumeratedPath();
        enumeratedPath.setFlow(flowRef);
        enumeratedPath.setPathSequence(request.getPathSequence());

        enumeratedPathRepository.save(enumeratedPath);

        return enumeratedPathMapper.toSimpleDto(enumeratedPath);
    }

    public EnumPathDto update(Long id, UpdateEnumPathDto request) {

        EnumeratedPath enumeratedPathFromDb = enumeratedPathRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Path step not found with id: " + id));

        Flow flowRef = flowService.getFlowFromDb(request.getFlowId());

        enumeratedPathFromDb.setFlow(flowRef);
        enumeratedPathFromDb.setPathSequence(request.getPathSequence());

        enumeratedPathRepository.save(enumeratedPathFromDb);

        return enumeratedPathMapper.toSimpleDto(enumeratedPathFromDb);
    }

}
