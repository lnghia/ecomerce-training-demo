package com.example.demo.dto.responses.product;

import com.example.demo.entities.CategoryEntity;
import com.example.demo.entities.GenderEntity;
import com.example.demo.entities.SportEntity;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProductResponseDto {
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

    private Set<ProductSizeResponseDto> sizes;

    private CategoryEntity category;
}
