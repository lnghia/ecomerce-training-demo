package com.example.demo.services.interfaces;

import com.example.demo.dto.requests.CreateProductRequestDTO;
import com.example.demo.dto.responses.ProductResponseDTO;

public interface ProductCRUDService {
    ProductResponseDTO createProduct(CreateProductRequestDTO createProductRequestDTO);

//    ProductResponseDTO addSizeToProduct(AddSizeToProductRequestDto addSizeToProductRequestDto);
}
