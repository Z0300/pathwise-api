package com.api.pathwise.mapper;

import com.api.pathwise.dto.flow.FlowDto;
import com.api.pathwise.entity.Flow;
import org.springframework.stereotype.Component;

@Component
public class FlowMapper {
    public FlowDto toSimpleDto(Flow flow) {
        return FlowDto.builder()
                .id(flow.getId())
                .name(flow.getName())
                .description(flow.getDescription())
                .createdAt(flow.getCreatedAt())
                .build();
    }
}
