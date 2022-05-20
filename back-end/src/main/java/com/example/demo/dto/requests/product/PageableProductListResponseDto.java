package com.example.demo.dto.requests.product;

import com.example.demo.dto.responses.product.ProductResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageableProductListResponseDto {
  private Long totalElements;

  private int totalPages;

  private int size;

  private List<ProductResponseDto> content;
}
