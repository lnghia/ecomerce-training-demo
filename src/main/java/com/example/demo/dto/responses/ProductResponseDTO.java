package com.example.demo.dto.responses;

import com.example.demo.entities.*;

import java.util.Set;

public class ProductResponseDTO {
    private Long id;

    private GenderEntity gender;

    private String name;

    private String description;

    private int oneStarRating;

    private int twoStarRating;

    private int threeStarRating;

    private int fourStarRating;

    private int fiveStarRating;

    private double averageRating;

    private double price;

    private int year;

    private SportEntity sport;

    private UpperEntity upper;

    private MidsoleEntity midsole;

    private Set<CollectionEntity> collections;

    private Set<CategoryEntity> categories;

    private Set<ProductSizeEntity> sizes;
}
