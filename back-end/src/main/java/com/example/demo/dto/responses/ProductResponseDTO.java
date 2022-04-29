package com.example.demo.dto.responses;

import com.example.demo.entities.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
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

    private CollectionEntity collection;

    private CategoryEntity category;
}
