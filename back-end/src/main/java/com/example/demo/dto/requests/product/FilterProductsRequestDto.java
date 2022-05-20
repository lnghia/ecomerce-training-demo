package com.example.demo.dto.requests.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilterProductsRequestDto {
  private String name;

  private List<Long> genderIds;

  private List<Long> sportIds;

  private List<Long> categoryIds;

  private List<Long> technologyIds;

  private int page = 0;

  private int size = 9;

  private String sortType;

  private String sortBy;
}
