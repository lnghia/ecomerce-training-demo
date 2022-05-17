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
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@Getter
public class CategoryCrudServiceImpl implements CategoryCrudService {
    private CategoryDatabaseService categoryDatabaseService;

    private CategoryRepository categoryRepository;

    private CommonConverter modelMapper;

    private ConverterUtil converterUtil;

    @Autowired
    public CategoryCrudServiceImpl(CategoryDatabaseService categoryDatabaseService,
                                   CategoryRepository categoryRepository,
                                   CommonConverter modelMapper,
                                   ConverterUtil converterUtil) {
        this.categoryDatabaseService = categoryDatabaseService;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.converterUtil = converterUtil;
    }

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
