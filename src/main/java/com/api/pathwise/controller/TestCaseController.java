package com.api.pathwise.controller;

import com.api.pathwise.dto.ApiResponse;
import com.api.pathwise.dto.tescase.CreateTestCaseDto;
import com.api.pathwise.dto.tescase.TestCaseDto;
import com.api.pathwise.dto.tescase.UpdateTestCaseDto;
import com.api.pathwise.service.TestCaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/test-cases")
@RequiredArgsConstructor
public class TestCaseController {
    private final TestCaseService testCaseService;

    @GetMapping
    public ResponseEntity<ApiResponse.Success<List<TestCaseDto>>> getAll(
            Pageable pageable) {

        Page<TestCaseDto> page = testCaseService.getAll(pageable);

        return ResponseEntity.ok(ApiResponse.Success.<List<TestCaseDto>>builder()
                .data(page.getContent())
                .meta(ApiResponse.Meta.builder()
                        .page(page.getNumber())
                        .size(page.getSize())
                        .totalElements(page.getTotalElements())
                        .totalPages(page.getTotalPages())
                        .build())
                .build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse.Success<TestCaseDto>> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.Success.<TestCaseDto>builder()
                .data(testCaseService.getById(id))
                .build());
    }


    @PostMapping
    public ResponseEntity<ApiResponse.Success<TestCaseDto>> create(
            @Valid @RequestBody CreateTestCaseDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.Success.<TestCaseDto>builder()
                        .message("created")
                        .data(testCaseService.create(request))
                        .build());
    }


    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse.Success<TestCaseDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTestCaseDto request) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                ApiResponse.Success.<TestCaseDto>builder()
                        .message("updated")
                        .data(testCaseService.update(id, request))
                        .build());
    }
}
