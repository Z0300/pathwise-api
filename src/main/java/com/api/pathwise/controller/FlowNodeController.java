package com.api.pathwise.controller;

import com.api.pathwise.dto.ApiResponse;
import com.api.pathwise.dto.flownode.CreateFlowNodeDto;
import com.api.pathwise.dto.flownode.FlowNodeDto;
import com.api.pathwise.dto.flownode.UpdateFlowNodeDto;
import com.api.pathwise.service.FlowNodeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flow-nodes")
@RequiredArgsConstructor
public class FlowNodeController {

    private final FlowNodeService flowNodeService;

    @GetMapping
    public ResponseEntity<ApiResponse.Success<List<FlowNodeDto>>> getAll(
            Pageable pageable) {

        Page<FlowNodeDto> page = flowNodeService.getAll(pageable);

        return ResponseEntity.ok(ApiResponse.Success.<List<FlowNodeDto>>builder()
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
    public ResponseEntity<ApiResponse.Success<FlowNodeDto>> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.Success.<FlowNodeDto>builder()
                .data(flowNodeService.getById(id))
                .build());
    }


    @PostMapping
    public ResponseEntity<ApiResponse.Success<FlowNodeDto>> create(
            @Valid @RequestBody CreateFlowNodeDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.Success.<FlowNodeDto>builder()
                        .message("created")
                        .data(flowNodeService.create(request))
                        .build());
    }


    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse.Success<FlowNodeDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateFlowNodeDto request) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                ApiResponse.Success.<FlowNodeDto>builder()
                        .message("updated")
                        .data(flowNodeService.update(id, request))
                        .build());
    }


}
