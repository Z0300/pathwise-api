package com.api.pathwise.dto.flownode;

import com.api.pathwise.dto.NodeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateFlowNodeDto {
    @NotNull(message = "id is required")
    private Long id;
    @NotNull(message = "flowId is required")
    private Long flowId;
    @NotBlank(message = "Node key is required")
    private String nodeKey;
    private NodeType nodeType;
    private String label;
}
