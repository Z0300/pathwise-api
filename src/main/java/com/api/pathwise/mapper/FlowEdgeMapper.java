package com.api.pathwise.mapper;

import com.api.pathwise.dto.flowedge.FlowEdgeDto;
import com.api.pathwise.entity.FlowEdge;
import org.springframework.stereotype.Component;

@Component
public class FlowEdgeMapper {
    public FlowEdgeDto toSimpleDto(FlowEdge flowEdge) {
        return FlowEdgeDto.builder()
                .id(flowEdge.getId())
                .flowId(flowEdge.getFlow().getId())
                .fromNodeId(flowEdge.getFlow().getId())
                .toNodeId(flowEdge.getFlow().getId())
                .conditionLabel(flowEdge.getConditionLabel())
                .build();
    }
}
