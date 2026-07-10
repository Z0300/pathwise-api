package com.api.pathwise.dto.flow;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFlowDto {

    @NotNull
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 255)
    private String name;

    private String description;
}
