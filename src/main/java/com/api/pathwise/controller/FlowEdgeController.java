package com.api.pathwise.controller;

import com.api.pathwise.dto.ApiResponse;
import com.api.pathwise.dto.flowedge.CreateFlowEdgeDto;
import com.api.pathwise.dto.flowedge.FlowEdgeDto;
import com.api.pathwise.dto.flowedge.UpdateFlowEdgeDto;
import com.api.pathwise.service.FlowEdgeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flow-edges")
@RequiredArgsConstructor
public class FlowEdgeController {

    private final FlowEdgeService flowEdgeService;
    @GetMapping
    public ResponseEntity<ApiResponse.Success<List<FlowEdgeDto>>> getAll(
            Pageable pageable) {

        Page<FlowEdgeDto> page = flowEdgeService.getAll(pageable);

        return ResponseEntity.ok(ApiResponse.Success.<List<FlowEdgeDto>>builder()
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
    public ResponseEntity<ApiResponse.Success<FlowEdgeDto>> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.Success.<FlowEdgeDto>builder()
                .data(flowEdgeService.getById(id))
                .build());
    }


    @PostMapping
    public ResponseEntity<ApiResponse.Success<FlowEdgeDto>> create(
            @Valid @RequestBody CreateFlowEdgeDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.Success.<FlowEdgeDto>builder()
                        .message("created")
                        .data(flowEdgeService.create(request))
                        .build());
    }


    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse.Success<FlowEdgeDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateFlowEdgeDto request) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                ApiResponse.Success.<FlowEdgeDto>builder()
                        .message("updated")
                        .data(flowEdgeService.update(id, request))
                        .build());
    }
}
