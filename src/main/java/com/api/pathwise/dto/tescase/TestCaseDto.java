package com.api.pathwise.dto.tescase;
import com.api.pathwise.dto.TestStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
@Builder
public class TestCaseDto {
    private Long id;
    private String name;
    private Long enumeratedPathId;
    private String title;
    private String description;
    private Map<String, Object> testData;
    private String expectedResult;
    private TestStatus status;
}
