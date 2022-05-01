package com.example.demo.services.interfaces.product;

import com.example.demo.dto.responses.product.ProductResponseDto;

import java.util.List;

public interface ProductService {
    ProductResponseDto findById(long id);

    List<ProductResponseDto> findAllWithFilterAndSort(List<Long> categoryIds,
                                                      Long genderId,
                                                      Long sportId,
                                                      List<Long> technologyIds,
                                                      String name,
                                                      int page,
                                                      int size,
                                                      String sortType,
                                                      String sortBy);
}
