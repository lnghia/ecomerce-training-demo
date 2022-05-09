package com.example.demo.dto.requests.category;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UpdateCategoryRequestDto {
    @NotNull
    private Long categoryId;

    @Pattern(regexp = "\\p{L}")
    private String name;

    private String description;
}
