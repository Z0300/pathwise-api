package com.api.pathwise.dto.enumpath;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class EnumPathDto {
    private Long id;
    private Long flowId;
    private List<PathStep> pathSequence;
    private LocalDateTime createdAt;
}
