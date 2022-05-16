package com.example.demo.services.interfaces.category;

import com.example.demo.entities.CategoryEntity;

import java.util.List;

public interface CategoryDatabaseService {
    CategoryEntity findById(Long id);

    CategoryEntity save(CategoryEntity categoryEntity);

    List<CategoryEntity> findAllById(List<Long> ids);

    List<CategoryEntity> findAll();
}
