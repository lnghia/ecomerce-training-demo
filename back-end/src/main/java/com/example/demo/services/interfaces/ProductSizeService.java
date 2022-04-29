package com.example.demo.services.interfaces;

import com.example.demo.dto.requests.AddSizeToProductRequestDto;
import com.example.demo.entities.ProductEntity;

public interface ProductSizeService {
    void createProductSize(ProductEntity productEntity, Long sizeId, int inStock);

    boolean addSizeToProduct(AddSizeToProductRequestDto addSizeToProductRequestDto);
}
