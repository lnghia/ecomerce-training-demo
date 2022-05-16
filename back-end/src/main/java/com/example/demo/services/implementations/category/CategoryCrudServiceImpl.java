package com.example.demo.services.implementations.category;

import com.example.demo.configurations.modelmapper.converters.CommonConverter;
import com.example.demo.dto.requests.category.CreateCategoryRequestDto;
import com.example.demo.dto.requests.category.UpdateCategoryRequestDto;
import com.example.demo.dto.responses.category.CategoryResponseDto;
import com.example.demo.entities.CategoryEntity;
import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.interfaces.category.CategoryCrudService;
import com.example.demo.utilities.converter.ConverterUtil;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class CategoryCrudServiceImpl implements CategoryCrudService {
    private CategoryRepository categoryRepository;

    private CommonConverter modelMapper;

    private ConverterUtil converterUtil;

    @Autowired
    public CategoryCrudServiceImpl(CategoryRepository categoryRepository,
                                   CommonConverter modelMapper,
                                   ConverterUtil converterUtil) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.converterUtil = converterUtil;
    }

    @Override
    public List<CategoryEntity> findByIds(List<Long> ids) {
        List<CategoryEntity> categoryEntities = categoryRepository.findAllById(ids);

        if (categoryEntities.size() != ids.size()) {
            throw new CategoryNotFoundException();
        }

        return categoryEntities;
    }

    @Override
    public List<CategoryResponseDto> findAll() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        List<CategoryResponseDto> result;

        result = converterUtil.buildListOfType(categoryEntities, CategoryResponseDto.class);

        return result;
    }

    @Override
    public CategoryResponseDto createCategory(CreateCategoryRequestDto requestDto) {
        CategoryEntity categoryEntity = modelMapper.convertToEntity(requestDto, CategoryEntity.class);
        categoryEntity = categoryRepository.save(categoryEntity);

        return modelMapper.convertToResponse(categoryEntity, CategoryResponseDto.class);
    }

    @Override
    public CategoryResponseDto updateCategory(Long categoryId, UpdateCategoryRequestDto requestDto) {
        CategoryEntity categoryEntity = findById(categoryId);

        modelMapper.convertToEntity(requestDto, categoryEntity);
        categoryEntity = categoryRepository.save(categoryEntity);

        return modelMapper.convertToResponse(categoryEntity, CategoryResponseDto.class);
    }

    @Override
    public CategoryEntity findById(Long id) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(id);

        if (!categoryEntity.isPresent()) {
            throw new CategoryNotFoundException();
        }

        return categoryEntity.get();
    }


}
