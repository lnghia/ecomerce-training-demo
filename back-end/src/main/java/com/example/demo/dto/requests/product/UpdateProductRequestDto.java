package com.example.demo.dto.requests.product;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class UpdateProductRequestDto {
  @NotNull private Long productId;

  private Long genderId;

  private String name;

  private String description;

  private double price;

  private int year;

  private Long sportId;

  private List<Long> categoryIds;

  private List<Long> technologyIds;

  private List<ProductSizeDto> productSizeDtoList;

  private String thumbnail;
}
