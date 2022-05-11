package com.example.demo.services.interfaces.category;

import com.example.demo.dto.requests.category.CreateCategoryRequestDto;
import com.example.demo.dto.requests.category.UpdateCategoryRequestDto;
import com.example.demo.dto.responses.category.CategoryResponseDto;
import com.example.demo.entities.CategoryEntity;

import java.util.List;

public interface CategoryCrudService {
    List<CategoryEntity> findByIds(List<Long> ids);

    List<CategoryResponseDto> findAll();

    CategoryResponseDto createCategory(CreateCategoryRequestDto requestDto);

    CategoryResponseDto updateCategory(UpdateCategoryRequestDto requestDto);

    CategoryEntity findById(Long id);
}
