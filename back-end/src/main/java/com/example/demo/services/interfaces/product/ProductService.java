package com.example.demo.services.interfaces.product;

import com.example.demo.dto.responses.product.ProductResponseDto;

public interface ProductService {
    ProductResponseDto findById(long id);
}
