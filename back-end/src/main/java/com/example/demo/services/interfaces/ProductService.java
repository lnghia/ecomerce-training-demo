package com.example.demo.services.interfaces;

import com.example.demo.dto.responses.ProductResponseDTO;

public interface ProductService {
    ProductResponseDTO findById(long id);
}
