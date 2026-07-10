package com.api.pathwise.controller;

import com.api.pathwise.dto.ApiResponse;
import com.api.pathwise.dto.flows.CreateFlowDto;
import com.api.pathwise.dto.flows.FlowDto;
import com.api.pathwise.dto.flows.UpdateFlowDto;
import com.api.pathwise.service.FlowService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flows")
@RequiredArgsConstructor
public class FlowController {

    private final FlowService flowService;

    @GetMapping
    public ResponseEntity<ApiResponse.Success<List<FlowDto>>> getAll(
           @RequestParam(required = false) String searchTerm,
           Pageable pageable) {

        Page<FlowDto> page = flowService.getAll(searchTerm, pageable);

        return ResponseEntity.ok(ApiResponse.Success.<List<FlowDto>>builder()
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
    public ResponseEntity<ApiResponse.Success<FlowDto>> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.Success.<FlowDto>builder()
                .data(flowService.getById(id))
                .build());
    }


    @PostMapping
    public ResponseEntity<ApiResponse.Success<FlowDto>> create(
            @Valid @RequestBody CreateFlowDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.Success.<FlowDto>builder()
                        .message("created")
                        .data(flowService.create(request))
                        .build());
    }


    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse.Success<FlowDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateFlowDto request) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                ApiResponse.Success.<FlowDto>builder()
                        .message("updated")
                        .data(flowService.update(id, request))
                        .build());
    }



}
