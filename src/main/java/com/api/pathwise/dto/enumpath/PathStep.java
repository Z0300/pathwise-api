package com.api.pathwise.dto.enumpath;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PathStep {
    private Long nodeId;
    private String nodeKey;
    private String conditionTaken;
}