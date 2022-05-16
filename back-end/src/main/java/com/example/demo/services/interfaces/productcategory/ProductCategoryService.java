package com.example.demo.services.interfaces.productcategory;

import com.example.demo.entities.ProductEntity;

import java.util.List;

public interface ProductCategoryService {
    void updateProductCategories(ProductEntity productEntity, List<Long> categoryIds);
}
