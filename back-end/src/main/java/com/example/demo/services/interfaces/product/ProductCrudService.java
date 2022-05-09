package com.example.demo.services.interfaces.product;

import com.example.demo.dto.requests.product.CreateProductRequestDto;
import com.example.demo.dto.requests.product.UpdateProductRequestDto;
import com.example.demo.dto.responses.product.ProductResponseDto;
import com.example.demo.entities.ProductEntity;

public interface ProductCrudService {
    ProductResponseDto createProduct(CreateProductRequestDto createProductRequestDTO);

    ProductResponseDto updateProduct(UpdateProductRequestDto updateProductRequestDto);

    ProductEntity findById(Long id);

    Boolean deleteProduct(Long id);

//    ProductResponseDTO addSizeToProduct(AddSizeToProductRequestDto addSizeToProductRequestDto);
}
