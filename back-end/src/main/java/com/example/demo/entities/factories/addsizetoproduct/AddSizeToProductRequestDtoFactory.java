package com.example.demo.entities.factories.addsizetoproduct;

import com.example.demo.dto.requests.product.AddSizeToProductRequestDto;
import com.example.demo.dto.requests.product.ProductSizeDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddSizeToProductRequestDtoFactory {
  public AddSizeToProductRequestDto createAddSizeToRequestDto(
      Long productId, List<ProductSizeDto> productSizeDtoList) {
    return AddSizeToProductRequestDto.builder()
        .productId(productId)
        .productSizeDto(productSizeDtoList)
        .build();
  }
}
