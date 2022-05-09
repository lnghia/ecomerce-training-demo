package com.example.demo.dto.requests.gender;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateGenderRequestDto {
    @NotNull
    private Long genderId;

    @NotNull
    private String name;
}
