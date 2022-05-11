package com.example.demo.services.interfaces.product;

import com.example.demo.dto.requests.product.CreateProductRequestDto;
import com.example.demo.dto.requests.product.ProductSizeDto;
import com.example.demo.dto.requests.product.UpdateProductRequestDto;
import com.example.demo.dto.responses.product.ProductResponseDto;
import com.example.demo.entities.ProductEntity;

import java.util.List;

public interface ProductCrudService {
    ProductResponseDto createProduct(CreateProductRequestDto createProductRequestDTO);

    ProductResponseDto updateProduct(UpdateProductRequestDto updateProductRequestDto);

    ProductEntity findById(Long id);

    Boolean deleteProduct(Long id);

    ProductEntity saveProduct(ProductEntity product);

    void updateProductCategory(ProductEntity product, List<Long> categoryIds);

    void updateProductTechnology(ProductEntity product, List<Long> technologyIds);

    void updateProductSize(ProductEntity product, List<ProductSizeDto> productSizeDtoList);

//    ProductResponseDTO addSizeToProduct(AddSizeToProductRequestDto addSizeToProductRequestDto);
}
