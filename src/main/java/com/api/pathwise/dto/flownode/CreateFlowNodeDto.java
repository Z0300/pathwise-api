package com.api.pathwise.dto.flownode;

import com.api.pathwise.dto.NodeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateFlowNodeDto {
    @NotNull(message = "Flow id is required")
    private Long flowId;
    @NotBlank(message = "Node key is required")
    private String nodeKey;
    @NotNull(message = "Node type is required")
    private NodeType nodeType;
    private String label;
}
