package com.example.demo.services.implementations.category;

import com.example.demo.entities.CategoryEntity;
import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.interfaces.category.CategoryDatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryDatabaseServiceImpl implements CategoryDatabaseService {
    private final CategoryRepository categoryRepository;

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
