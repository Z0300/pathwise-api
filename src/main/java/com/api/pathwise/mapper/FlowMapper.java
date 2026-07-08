package com.api.pathwise.mapper;

import com.api.pathwise.dto.FlowResponse;
import com.api.pathwise.entity.Flow;
import org.springframework.stereotype.Component;

@Component
public class FlowMapper {
    public FlowResponse toSimpleDto(Flow flow) {
        return FlowResponse.builder()
                .id(flow.getId())
                .name(flow.getName())
                .description(flow.getDescription())
                .createdAt(flow.getCreatedAt())
                .build();
    }
}
