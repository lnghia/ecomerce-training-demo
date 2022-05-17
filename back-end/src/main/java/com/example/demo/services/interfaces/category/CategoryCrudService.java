package com.example.demo.services.interfaces.category;

import com.example.demo.dto.requests.category.CreateCategoryRequestDto;
import com.example.demo.dto.requests.category.UpdateCategoryRequestDto;
import com.example.demo.dto.responses.category.CategoryResponseDto;

import java.util.List;

public interface CategoryCrudService {
    CategoryResponseDto createCategory(CreateCategoryRequestDto requestDto);

    CategoryResponseDto updateCategory(Long categoryId, UpdateCategoryRequestDto requestDto);

    List<CategoryResponseDto> getAll();
}
