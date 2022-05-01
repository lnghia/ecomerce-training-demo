package com.example.demo.services.implementations;

import com.example.demo.entities.CategoryEntity;
import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.interfaces.CategoryCrudService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@NoArgsConstructor
public class CategoryCrudServiceImpl implements CategoryCrudService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryCrudServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List findByIds(Set<Long> ids) {
        int countExist = 0;
        List<CategoryEntity> categoryEntities = categoryRepository.findAllById(ids);

        if (categoryEntities.size() != ids.size()) {
            throw new CategoryNotFoundException();
        }

        return categoryEntities;
    }
}
