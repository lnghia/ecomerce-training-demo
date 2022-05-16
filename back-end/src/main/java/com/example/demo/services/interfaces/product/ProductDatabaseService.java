package com.example.demo.services.interfaces.product;

import com.example.demo.entities.ProductEntity;

public interface ProductDatabaseService {
    ProductEntity findById(Long id);

    ProductEntity saveProduct(ProductEntity productEntity);

    boolean deleteProduct(Long id);
}
