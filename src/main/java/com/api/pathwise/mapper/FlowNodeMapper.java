package com.api.pathwise.mapper;

import com.api.pathwise.dto.flownode.FlowNodeDto;
import com.api.pathwise.entity.FlowNode;
import org.springframework.stereotype.Component;

@Component
public class FlowNodeMapper {
    public FlowNodeDto toSimpleDto(FlowNode flowNode) {
        return FlowNodeDto.builder()
                .id(flowNode.getId())
                .flowId(flowNode.getFlow().getId())
                .nodeKey(flowNode.getNodeKey())
                .nodeType(flowNode.getNodeType())
                .label(flowNode.getLabel())
                .build();
    }
}
