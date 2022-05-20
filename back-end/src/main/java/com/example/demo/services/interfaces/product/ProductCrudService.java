package com.example.demo.services.interfaces.product;

import com.example.demo.dto.requests.product.CreateProductRequestDto;
import com.example.demo.dto.requests.product.UpdateProductRequestDto;
import com.example.demo.dto.responses.product.ProductResponseDto;

public interface ProductCrudService {
  ProductResponseDto createProduct(CreateProductRequestDto createProductRequestDTO);

  ProductResponseDto updateProduct(UpdateProductRequestDto updateProductRequestDto);

  Boolean deleteProduct(Long id);
}
