package com.api.pathwise.mapper;

import com.api.pathwise.dto.enumpath.EnumPathDto;
import com.api.pathwise.entity.EnumeratedPath;
import org.springframework.stereotype.Component;

@Component
public class EnumeratedPathMapper {
    public EnumPathDto toSimpleDto(EnumeratedPath enumeratedPath) {
        return EnumPathDto.builder()
                .id(enumeratedPath.getId())
                .flowId(enumeratedPath.getFlow().getId())
                .pathSequence(enumeratedPath.getPathSequence())
                .createdAt(enumeratedPath.getCreatedAt())
                .build();
    }
}
