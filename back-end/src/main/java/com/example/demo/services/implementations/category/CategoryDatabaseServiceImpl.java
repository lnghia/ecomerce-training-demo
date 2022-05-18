package com.example.demo.services.implementations.category;

import com.example.demo.entities.CategoryEntity;
import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.interfaces.category.CategoryDatabaseService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class CategoryDatabaseServiceImpl implements CategoryDatabaseService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryDatabaseServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryEntity findById(Long id) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(id);

        if (categoryEntity.isEmpty()) {
            throw new CategoryNotFoundException();
        }

        return categoryEntity.get();
    }

    @Override
    public List<CategoryEntity> findAllById(List<Long> ids) {
        return categoryRepository.findAllById(ids);
    }

    @Override
    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }
}
