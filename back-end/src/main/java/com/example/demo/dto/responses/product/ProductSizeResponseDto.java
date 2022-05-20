package com.example.demo.dto.responses.product;

import com.example.demo.entities.ProductEntity;
import com.example.demo.entities.SizeEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSizeResponseDto {
  private SizeEntity size;

  private int inStock;

  private ProductEntity product;
}
