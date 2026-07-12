package com.api.pathwise.service;

import com.api.pathwise.dto.tescase.CreateTestCaseDto;
import com.api.pathwise.dto.tescase.TestCaseDto;
import com.api.pathwise.dto.tescase.UpdateTestCaseDto;
import com.api.pathwise.entity.EnumeratedPath;
import com.api.pathwise.entity.TestCase;
import com.api.pathwise.exception.ResourceNotFoundException;
import com.api.pathwise.mapper.TestCaseMapper;
import com.api.pathwise.repository.TestCaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestCaseService {
    private final TestCaseRepository testCaseRepository;
    private final EnumeratedPathService enumeratedPathService;
    private final TestCaseMapper testCaseMapper;

    public Page<TestCaseDto> getAll(Pageable pageable) {
        return testCaseRepository.findAll(pageable)
                .map(testCaseMapper::toSimpleDto);
    }

    public TestCaseDto getById(Long id) {
        TestCase testCase = getTestCase(id);
        return testCaseMapper.toSimpleDto(testCase);
    }


    public TestCaseDto create(CreateTestCaseDto request) {
        EnumeratedPath pathRef = enumeratedPathService.getEnumeratedPath(request.getPathId());

        TestCase testCase = new TestCase();
        testCase.setEnumeratedPath(pathRef);
        testCase.setTitle(request.getTitle());
        testCase.setDescription(request.getDescription());
        testCase.setTestData(request.getTestData());
        testCase.setExpectedResult(request.getExpectedResult());
        testCase.setStatus(request.getStatus());
        testCaseRepository.save(testCase);

        return testCaseMapper.toSimpleDto(testCase);
    }

    public TestCaseDto update(Long id, UpdateTestCaseDto request) {
        TestCase testCase = getTestCase(id);
        EnumeratedPath pathRef = enumeratedPathService.getEnumeratedPath(request.getPathId());

        testCase.setEnumeratedPath(pathRef);
        testCase.setTitle(request.getTitle());
        testCase.setDescription(request.getDescription());
        testCase.setTestData(request.getTestData());
        testCase.setExpectedResult(request.getExpectedResult());
        testCase.setStatus(request.getStatus());

        testCaseRepository.save(testCase);

        return testCaseMapper.toSimpleDto(testCase);
    }


    private @NonNull TestCase getTestCase(Long id) {
        return testCaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id));
    }
}
