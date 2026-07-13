package com.api.pathwise.dto.flowedge;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FlowEdgeDto {
    private Long id;
    private Long flowId;
    private Long fromNodeId;
    private Long toNodeId;
    private String conditionLabel;
}
