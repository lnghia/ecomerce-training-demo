package com.example.demo.services.interfaces.producttechnology;

import com.example.demo.entities.ProductEntity;

import java.util.List;

public interface ProductTechnologyService {
    void updateProductTechnologies(ProductEntity productEntity, List<Long> technologyIds);
}
