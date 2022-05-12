package com.example.demo.dto.requests.sport;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateSportRequestDto {
    @NotNull
    private Long id;

    @NotNull
    @NotEmpty
    private String name;
}
