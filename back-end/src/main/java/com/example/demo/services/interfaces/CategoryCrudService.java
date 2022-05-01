package com.example.demo.services.interfaces;

import com.example.demo.entities.CategoryEntity;

import java.util.List;
import java.util.Set;

public interface CategoryCrudService {
    List<CategoryEntity> findByIds(Set<Long> ids);
}
