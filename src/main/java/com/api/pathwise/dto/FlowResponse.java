package com.api.pathwise.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class FlowResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
}
