package com.example.demo.services.implementations.category;

import com.example.demo.configurations.modelmapper.converters.CommonConverter;
import com.example.demo.dto.requests.category.CreateCategoryRequestDto;
import com.example.demo.dto.requests.category.UpdateCategoryRequestDto;
import com.example.demo.dto.responses.category.CategoryResponseDto;
import com.example.demo.entities.CategoryEntity;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.interfaces.category.CategoryCrudService;
import com.example.demo.services.interfaces.category.CategoryDatabaseService;
import com.example.demo.utilities.converter.ConverterUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Getter
public class CategoryCrudServiceImpl implements CategoryCrudService {
    private final CategoryDatabaseService categoryDatabaseService;

    private final CategoryRepository categoryRepository;

    private final CommonConverter modelMapper;

    private final ConverterUtil converterUtil;

    @Override
    public CategoryResponseDto createCategory(CreateCategoryRequestDto requestDto) {
        CategoryEntity categoryEntity = modelMapper.convertToEntity(requestDto, CategoryEntity.class);
        categoryEntity = categoryRepository.save(categoryEntity);

        return modelMapper.convertToResponse(categoryEntity, CategoryResponseDto.class);
    }

    @Override
    public CategoryResponseDto updateCategory(Long categoryId, UpdateCategoryRequestDto requestDto) {
        CategoryEntity categoryEntity = categoryDatabaseService.findById(categoryId);

        modelMapper.convertToEntity(requestDto, categoryEntity);
        categoryEntity = categoryRepository.save(categoryEntity);

        return modelMapper.convertToResponse(categoryEntity, CategoryResponseDto.class);
    }

    @Override
    public List<CategoryResponseDto> getAll() {
        List<CategoryEntity> categoryEntities = categoryDatabaseService.findAll();

        return modelMapper.convertToResponseList(categoryEntities, CategoryResponseDto.class);
    }
}
