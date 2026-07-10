package com.api.pathwise.dto.flownode;

import com.api.pathwise.dto.NodeType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateFlowNodeDto {
    private Long flowId;
    private String nodeKey;
    private NodeType nodeType;
    private String label;
}
