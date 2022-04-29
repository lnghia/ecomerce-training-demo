package com.example.demo.services.interfaces;

import com.example.demo.entities.CategoryEntity;

public interface CategoryCrudService {
    CategoryEntity findById(Long id);
}
