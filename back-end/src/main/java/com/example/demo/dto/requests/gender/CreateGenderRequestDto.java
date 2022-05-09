package com.example.demo.dto.requests.gender;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class CreateGenderRequestDto {
    @NotNull
    @Pattern(regexp = "\\p{L}")
    private String name;
}
