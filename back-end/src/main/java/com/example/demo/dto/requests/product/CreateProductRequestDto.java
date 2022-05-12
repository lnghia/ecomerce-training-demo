package com.example.demo.dto.requests.product;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class CreateProductRequestDto {
    @NotNull
    private Long genderId;

    private String thumbnail;

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
    private List<Long> technologyIds;

    @NotNull
    private List<Long> categoryIds;

    @NotNull
    private List<ProductSizeDto> productSizeDtoList;
}
