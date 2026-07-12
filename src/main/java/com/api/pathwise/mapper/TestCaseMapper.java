package com.api.pathwise.mapper;
import com.api.pathwise.dto.tescase.TestCaseDto;
import com.api.pathwise.entity.TestCase;
import org.springframework.stereotype.Component;

@Component
public class TestCaseMapper {
    public TestCaseDto toSimpleDto(TestCase enumeratedPath) {
        return TestCaseDto.builder()
                .id(enumeratedPath.getId())
                .enumeratedPathId(enumeratedPath.getEnumeratedPath().getId())
                .title(enumeratedPath.getTitle())
                .description(enumeratedPath.getDescription())
                .testData(enumeratedPath.getTestData())
                .expectedResult(enumeratedPath.getExpectedResult())
                .status(enumeratedPath.getStatus())
                .build();
    }
}
