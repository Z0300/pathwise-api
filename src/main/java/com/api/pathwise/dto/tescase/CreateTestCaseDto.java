package com.api.pathwise.dto.tescase;

import com.api.pathwise.dto.TestStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
public class CreateTestCaseDto {
    @NotNull(message = "Path id is required")
    private Long pathId;
    @NotBlank(message = "Title is required")
    private String title;
    private String description;
    @NotNull(message = "Test data is required")
    private Map<String, Object> testData;
    @NotBlank(message = "Expected result is required")
    private String expectedResult;
    @NotNull(message = "Status is required")
    private TestStatus status;
}
