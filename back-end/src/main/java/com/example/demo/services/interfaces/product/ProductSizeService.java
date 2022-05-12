package com.example.demo.services.interfaces.product;

import com.example.demo.dto.requests.product.AddSizeToProductRequestDto;
import com.example.demo.entities.ProductEntity;

public interface ProductSizeService {
    void createProductSize(ProductEntity productEntity, Long sizeId, int inStock);

    ProductEntity addSizeToProduct(AddSizeToProductRequestDto addSizeToProductRequestDto);
}
