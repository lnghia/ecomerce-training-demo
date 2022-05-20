package com.example.demo.services.implementations.productcategory;

import com.example.demo.entities.CategoryEntity;
import com.example.demo.entities.ProductEntity;
import com.example.demo.services.interfaces.category.CategoryDatabaseService;
import com.example.demo.services.interfaces.productcategory.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {
  private final CategoryDatabaseService categoryDatabaseService;

  @Override
  public void updateProductCategories(ProductEntity productEntity, List<Long> categoryIds) {
    List<CategoryEntity> categoryEntities = categoryDatabaseService.findAllById(categoryIds);

    productEntity.getCategories().clear();
    productEntity.setCategories(new HashSet<>(categoryEntities));
  }
}
