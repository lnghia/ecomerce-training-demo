package com.example.demo.services.interfaces;

import com.example.demo.dto.responses.ProductResponseDto;

public interface ProductService {
    ProductResponseDto findById(long id);
}
