package com.api.pathwise.dto.flow;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
public class FlowDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
}
