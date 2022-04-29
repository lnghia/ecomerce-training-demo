package com.example.demo.dto.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateProductRequestDTO {
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
    private Long upperId;

    @NotNull
    private Long midsoleId;

    private Long collectionId;

    @NotNull
    private Long categoryId;

//    private ProductSizeNumberDto[] sizeIds;
}
