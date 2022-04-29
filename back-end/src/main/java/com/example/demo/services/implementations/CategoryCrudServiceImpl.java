package com.example.demo.services.implementations;

import com.example.demo.entities.CategoryEntity;
import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.interfaces.CategoryCrudService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class CategoryCrudServiceImpl implements CategoryCrudService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryCrudServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryEntity findById(Long id) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(id);

        if (categoryEntity.isPresent()) {
            return categoryEntity.get();
        }

        throw new CategoryNotFoundException();
    }
}
