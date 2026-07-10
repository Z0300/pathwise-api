package com.api.pathwise.dto.enumpath;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateEnumPathDto {
    @NotNull (message = "Id is required")
    private Long id;

    @NotNull(message = "Flow is required")
    private Long flowId;

    @NotEmpty(message = "Path sequence cannot be empty")
    private List<PathStep> pathSequence;
}
