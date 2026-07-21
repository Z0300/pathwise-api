package com.api.pathwise.controller;

import com.api.pathwise.dto.ApiResponse;
import com.api.pathwise.dto.enumpath.EnumPathDto;
import com.api.pathwise.dto.flow.CreateFlowDto;
import com.api.pathwise.dto.flow.FlowDto;
import com.api.pathwise.dto.flow.UpdateFlowDto;
import com.api.pathwise.dto.flownode.FlowNodeDto;
import com.api.pathwise.entity.EnumeratedPath;
import com.api.pathwise.entity.FlowNode;
import com.api.pathwise.mapper.EnumeratedPathMapper;
import com.api.pathwise.mapper.FlowNodeMapper;
import com.api.pathwise.service.FlowNodeService;
import com.api.pathwise.service.FlowService;
import com.api.pathwise.service.PathEnumeratorService;
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

    private final FlowNodeService flowNodeService;
    private final FlowNodeMapper flowNodeMapper;

    private final PathEnumeratorService pathEnumeratorService;
    private final EnumeratedPathMapper enumeratedPathMapper;

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

    // Flow enumeration

    @PostMapping("/{flowId}/enumerate")
    public ResponseEntity<ApiResponse.Success<List<EnumPathDto>>> enumerate(@PathVariable Long flowId) {
        List<EnumeratedPath> paths = pathEnumeratorService.enumerateAndSave(flowId);
        List<EnumPathDto> enumPathDto = paths.stream().map(enumeratedPathMapper::toSimpleDto).toList();
        return ResponseEntity.ok(ApiResponse.Success.<List<EnumPathDto>>builder()
                .success(true)
                .data(enumPathDto)
                .build());
    }

    // Flow nodes

    @GetMapping("/{flowId}/nodes")
    public ResponseEntity<ApiResponse.Success<List<FlowNodeDto>>> getByFlow(@PathVariable Long flowId) {
        List<FlowNode> nodes = flowNodeService.getNodesByFlowId(flowId);
        List<FlowNodeDto> flowNodeDto = nodes.stream().map(flowNodeMapper::toSimpleDto).toList();
        return ResponseEntity.ok(ApiResponse.Success.<List<FlowNodeDto>>builder()
                .success(true)
                .data(flowNodeDto)
                .build());
    }

}
