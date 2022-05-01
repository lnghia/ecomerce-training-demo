package com.example.demo.dto.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
public class CreateProductRequestDto {
    @NotNull
    private Long genderId;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private double price;

    @NotNull
    private int year;

    @NotNull
    private Long sportId;

    @NotNull
    private Set<Long> technologyIds;

    @NotNull
    private Set<Long> categoryIds;
}
