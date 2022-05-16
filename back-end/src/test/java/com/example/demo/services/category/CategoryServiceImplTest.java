package com.example.demo.services.category;

import com.example.demo.configurations.modelmapper.converters.CommonConverter;
import com.example.demo.dto.requests.category.CreateCategoryRequestDto;
import com.example.demo.dto.requests.category.UpdateCategoryRequestDto;
import com.example.demo.dto.responses.category.CategoryResponseDto;
import com.example.demo.entities.CategoryEntity;
import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.implementations.category.CategoryCrudServiceImpl;
import com.example.demo.utilities.converter.ConverterUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CategoryServiceImplTest {
    private CategoryRepository categoryRepository;

    private CommonConverter modelMapper;

    private CategoryCrudServiceImpl categoryCrudService;

    private ConverterUtil converterUtil;

    private List<CategoryEntity> categoryEntities;

    @BeforeEach
    public void setUp() {
        categoryRepository = mock(CategoryRepository.class);
        modelMapper = mock(CommonConverter.class);
        converterUtil = mock(ConverterUtil.class);
        categoryCrudService = new CategoryCrudServiceImpl(categoryRepository, modelMapper, converterUtil);

        categoryEntities = mock(List.class);
    }

    @Test
    public void findByIds_ShouldReturnCategoryList_WhenDataValid() {
        List<Long> ids = mock(List.class);

        when(categoryRepository.findAllById(ids)).thenReturn(categoryEntities);
        when(categoryEntities.size()).thenReturn(1);
        when(ids.size()).thenReturn(1);

        List<CategoryEntity> result = categoryCrudService.findByIds(ids);

        assertEquals(result, categoryEntities);
    }

    @Test
    public void findByIds_ShouldThrowCategoryNotFoundException_WhenDataInvalid() {
        List<Long> ids = mock(List.class);


        when(categoryRepository.findAllById(ids)).thenReturn(categoryEntities);
        when(categoryEntities.size()).thenReturn(0);
        when(ids.size()).thenReturn(1);

        assertThrows(CategoryNotFoundException.class, () -> {
            categoryCrudService.findByIds(ids);
        });
    }

    @Test
    public void findAll_ShouldReturnCategoryResponseDtoList_WhenDataValid() {
        List<CategoryResponseDto> expectedResult = mock(List.class);

        when(categoryRepository.findAll()).thenReturn(categoryEntities);
        when(converterUtil.buildListOfType(categoryEntities, CategoryResponseDto.class)).thenReturn(expectedResult);

        List<CategoryResponseDto> result = categoryCrudService.findAll();

        assertEquals(result, expectedResult);
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
        CategoryCrudServiceImpl spy = Mockito.spy(categoryCrudService);
        CategoryResponseDto expectedResult = mock(CategoryResponseDto.class);

        doReturn(categoryEntity).when(spy).findById(anyLong());
        when(categoryRepository.save(categoryEntity)).thenReturn(categoryEntity);
        when(modelMapper.convertToResponse(categoryEntity, CategoryResponseDto.class)).thenReturn(expectedResult);

        CategoryResponseDto result = spy.updateCategory(1L, requestDto);

        assertThat(result, is(expectedResult));
    }

    @Test
    public void findById_ShouldReturnCategoryEntity_WhenDataValid() {
        CategoryEntity categoryEntity = mock(CategoryEntity.class);

        when(categoryRepository.findById(1L)).thenReturn(Optional.ofNullable(categoryEntity));

        CategoryEntity result = categoryCrudService.findById(1L);

        assertThat(result, is(categoryEntity));
    }

    @Test
    public void findById_ShouldThrowCategoryNotFoundException_WhenDataInValid() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.ofNullable(null));

        assertThrows(CategoryNotFoundException.class, () -> {
            categoryCrudService.findById(1L);
        });
    }

    @Test
    void shouldCreateCategoryCrudServiceImpl() {
        CategoryCrudServiceImpl categoryCrudService = new CategoryCrudServiceImpl();

        assertNull(categoryCrudService.getCategoryRepository());
        assertNull(categoryCrudService.getModelMapper());
        assertNull(categoryCrudService.getConverterUtil());
    }
}
