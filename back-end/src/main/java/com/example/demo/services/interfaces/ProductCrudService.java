package com.example.demo.services.interfaces;

import com.example.demo.dto.requests.CreateProductRequestDto;
import com.example.demo.dto.responses.ProductResponseDto;

public interface ProductCrudService {
    ProductResponseDto createProduct(CreateProductRequestDto createProductRequestDTO);

//    ProductResponseDTO addSizeToProduct(AddSizeToProductRequestDto addSizeToProductRequestDto);
}
