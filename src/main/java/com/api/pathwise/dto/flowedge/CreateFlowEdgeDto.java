package com.api.pathwise.dto.flowedge;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateFlowEdgeDto {
    @NotNull(message = "Flow id is required")
    private Long flowId;
    @NotNull(message = "Source node id is required")
    private Long fromNodeId;
    @NotNull(message = "Target node id is required")
    private Long toNodeId;
    private String conditionLabel;
}
