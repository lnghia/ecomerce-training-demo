package com.example.demo.services.category;

import com.example.demo.configurations.modelmapper.converters.CommonConverter;
import com.example.demo.dto.requests.category.CreateCategoryRequestDto;
import com.example.demo.dto.requests.category.UpdateCategoryRequestDto;
import com.example.demo.dto.responses.category.CategoryResponseDto;
import com.example.demo.entities.CategoryEntity;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.implementations.category.CategoryCrudServiceImpl;
import com.example.demo.services.interfaces.category.CategoryDatabaseService;
import com.example.demo.utilities.converter.ConverterUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CategoryServiceImplTest {
    private CategoryDatabaseService categoryDatabaseService;

    private CommonConverter modelMapper;

    private CategoryCrudServiceImpl categoryCrudService;

    private CategoryRepository categoryRepository;

    private ConverterUtil converterUtil;

    private List<CategoryEntity> categoryEntities;

    @BeforeEach
    public void setUp() {
        categoryDatabaseService = mock(CategoryDatabaseService.class);
        modelMapper = mock(CommonConverter.class);
        converterUtil = mock(ConverterUtil.class);
        categoryRepository = mock(CategoryRepository.class);
        categoryCrudService = new CategoryCrudServiceImpl(categoryDatabaseService, categoryRepository, modelMapper, converterUtil);

        categoryEntities = mock(List.class);
    }

    @Test
    public void createCategory_ShouldReturnCategoryResponseDto_WhenDataValid() {
        CreateCategoryRequestDto requestDto = mock(CreateCategoryRequestDto.class);
        CategoryEntity categoryEntity = mock(CategoryEntity.class);
        CategoryResponseDto expectedResult = mock(CategoryResponseDto.class);

        when(modelMapper.convertToEntity(requestDto, CategoryEntity.class)).thenReturn(categoryEntity);
        when(categoryRepository.save(categoryEntity)).thenReturn(categoryEntity);
        when(modelMapper.convertToResponse(categoryEntity, CategoryResponseDto.class)).thenReturn(expectedResult);

        CategoryResponseDto result = categoryCrudService.createCategory(requestDto);

        assertThat(result, is(expectedResult));
    }


    @Test
    public void updateCategory_ShouldReturnCategoryResponseDto_WhenDataValid() {
        CategoryEntity categoryEntity = mock(CategoryEntity.class);
        UpdateCategoryRequestDto requestDto = mock(UpdateCategoryRequestDto.class);
        CategoryResponseDto expectedResult = mock(CategoryResponseDto.class);

        when(categoryDatabaseService.findById(1L)).thenReturn(categoryEntity);
        when(categoryRepository.save(categoryEntity)).thenReturn(categoryEntity);
        when(modelMapper.convertToResponse(categoryEntity, CategoryResponseDto.class)).thenReturn(expectedResult);

        CategoryResponseDto result = categoryCrudService.updateCategory(1L, requestDto);

        assertThat(result, is(expectedResult));
    }
}
