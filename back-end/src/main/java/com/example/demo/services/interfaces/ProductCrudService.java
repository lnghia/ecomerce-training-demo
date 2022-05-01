package com.example.demo.services.interfaces;

import com.example.demo.dto.requests.CreateProductRequestDto;
import com.example.demo.dto.requests.UpdateProductRequestDto;
import com.example.demo.dto.responses.ProductResponseDto;
import com.example.demo.entities.ProductEntity;

public interface ProductCrudService {
    ProductResponseDto createProduct(CreateProductRequestDto createProductRequestDTO);

    ProductResponseDto updateProduct(UpdateProductRequestDto updateProductRequestDto);

    ProductEntity findById(Long id);

//    ProductResponseDTO addSizeToProduct(AddSizeToProductRequestDto addSizeToProductRequestDto);
}
