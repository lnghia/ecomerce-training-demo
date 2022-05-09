package com.example.demo.dto.requests.category;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class CreateCategoryRequestDto {
    @NotNull
    @Pattern(regexp = "\\p{L}")
    private String name;

    @NotNull
    private String description;
}
