package com.api.pathwise.controller;

import com.api.pathwise.dto.ApiResponse;
import com.api.pathwise.dto.enumpath.CreateEnumPathDto;
import com.api.pathwise.dto.enumpath.EnumPathDto;
import com.api.pathwise.dto.enumpath.UpdateEnumPathDto;
import com.api.pathwise.service.EnumeratedPathService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/path-steps")
@RequiredArgsConstructor
public class EnumeratedPathController {
    private final EnumeratedPathService enumeratedPathService;

    @GetMapping
    public ResponseEntity<ApiResponse.Success<List<EnumPathDto>>> getAll(
            Pageable pageable) {

        Page<EnumPathDto> page = enumeratedPathService.getAll(pageable);

        return ResponseEntity.ok(ApiResponse.Success.<List<EnumPathDto>>builder()
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
    public ResponseEntity<ApiResponse.Success<EnumPathDto>> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.Success.<EnumPathDto>builder()
                .data(enumeratedPathService.getById(id))
                .build());
    }


    @PostMapping
    public ResponseEntity<ApiResponse.Success<EnumPathDto>> create(
            @Valid @RequestBody CreateEnumPathDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.Success.<EnumPathDto>builder()
                        .message("created")
                        .data(enumeratedPathService.create(request))
                        .build());
    }


    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse.Success<EnumPathDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateEnumPathDto request) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                ApiResponse.Success.<EnumPathDto>builder()
                        .message("updated")
                        .data(enumeratedPathService.update(id, request))
                        .build());
    }
}
