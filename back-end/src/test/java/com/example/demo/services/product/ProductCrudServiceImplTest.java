package com.example.demo.services.product;

import com.example.demo.configurations.modelmapper.converters.CommonConverter;
import com.example.demo.dto.requests.product.AddSizeToProductRequestDto;
import com.example.demo.dto.requests.product.CreateProductRequestDto;
import com.example.demo.dto.requests.product.ProductSizeDto;
import com.example.demo.dto.requests.product.UpdateProductRequestDto;
import com.example.demo.dto.responses.product.ProductResponseDto;
import com.example.demo.entities.*;
import com.example.demo.entities.factories.addsizetoproduct.AddSizeToProductRequestDtoFactory;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.implementations.product.ProductCrudServiceImpl;
import com.example.demo.services.interfaces.category.CategoryCrudService;
import com.example.demo.services.interfaces.gender.GenderCrudService;
import com.example.demo.services.interfaces.product.ProductSizeService;
import com.example.demo.services.interfaces.sport.SportCrudService;
import com.example.demo.services.interfaces.technology.TechnologyService;
import com.example.demo.utilities.converter.ConverterUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ProductCrudServiceImplTest {
    CommonConverter modelMapper;
    ProductCrudServiceImpl productCrudServiceImpl;
    GenderCrudService genderCrudService;
    SportCrudService sportCrudService;
    CategoryCrudService categoryCrudService;
    ProductRepository productRepository;
    TechnologyService technologyService;
    ProductSizeService productSizeService;
    CreateProductRequestDto createProductRequestDTO;
    ConverterUtil converterUtil;
    List<Long> categoryIds;
    List<Long> technologyIds;
    GenderEntity genderEntity;
    SportEntity sportEntity;
    List<CategoryEntity> categoryEntities;
    List<TechnologyEntity> technologyEntities;
    Set<TechnologyEntity> technologyEntitiesSet;
    Set<CategoryEntity> categoryEntitiesSet;
    List<ProductSizeDto> productSizeDtoList;
    ProductEntity initProduct;
    ProductEntity expectedProduct;
    AddSizeToProductRequestDto addSizeToProductRequestDto;
    AddSizeToProductRequestDtoFactory addSizeToProductRequestDtoFactory;
    ProductResponseDto resultProductDto;

    @BeforeEach
    public void beforeEach() {
        modelMapper = mock(CommonConverter.class);
        genderCrudService = mock(GenderCrudService.class);
        sportCrudService = mock(SportCrudService.class);
        categoryCrudService = mock(CategoryCrudService.class);
        productRepository = mock(ProductRepository.class);
        technologyService = mock(TechnologyService.class);
        productSizeService = mock(ProductSizeService.class);
        converterUtil = mock(ConverterUtil.class);
        addSizeToProductRequestDto = mock(AddSizeToProductRequestDto.class);
        addSizeToProductRequestDtoFactory = mock(AddSizeToProductRequestDtoFactory.class);
        productCrudServiceImpl = new ProductCrudServiceImpl(
                modelMapper,
                genderCrudService,
                sportCrudService,
                categoryCrudService,
                productRepository,
                technologyService,
                productSizeService,
                addSizeToProductRequestDtoFactory,
                converterUtil
        );

        createProductRequestDTO = mock(CreateProductRequestDto.class);
        genderEntity = mock(GenderEntity.class);
        sportEntity = mock(SportEntity.class);
        categoryIds = mock(List.class);
        technologyIds = mock(List.class);
        categoryEntities = Arrays.asList(CategoryEntity.builder().id(1L).name("category").description("description").build());
        categoryEntitiesSet = categoryEntities.stream().collect(Collectors.toSet());
        technologyEntities = Arrays.asList(TechnologyEntity.builder().id(1L).name("technology").description("description").build());
        technologyEntitiesSet = technologyEntities.stream().collect(Collectors.toSet());
        expectedProduct = ProductEntity.builder()
                .id(1L)
                .name("product")
                .price(1)
                .year(2022)
                .thumbnail("thumbnail")
                .sport(sportEntity)
                .gender(genderEntity)
                .technologies(technologyEntitiesSet)
                .categories(categoryEntitiesSet)
                .description("description")
                .build();
        productSizeDtoList = mock(List.class);
        addSizeToProductRequestDto = mock(AddSizeToProductRequestDto.class);
        resultProductDto = mock(ProductResponseDto.class);

        when(createProductRequestDTO.getGenderId()).thenReturn(1L);
        when(createProductRequestDTO.getSportId()).thenReturn(2L);
        when(createProductRequestDTO.getCategoryIds()).thenReturn(categoryIds);
        when(createProductRequestDTO.getTechnologyIds()).thenReturn(technologyIds);
        when(createProductRequestDTO.getProductSizeDtoList()).thenReturn(productSizeDtoList);

        when(genderCrudService.findById(1L)).thenReturn(genderEntity);
        when(sportCrudService.findById(1L)).thenReturn(sportEntity);
        when(categoryCrudService.findByIds(categoryIds)).thenReturn(categoryEntities);
        when(technologyService.findByIds(technologyIds)).thenReturn(technologyEntities);

        when(modelMapper.convertToEntity(createProductRequestDTO, ProductEntity.class)).thenReturn(expectedProduct);
        when(productRepository.save(any())).thenReturn(expectedProduct);

        when(createProductRequestDTO.getProductSizeDtoList()).thenReturn(productSizeDtoList);
        when(addSizeToProductRequestDtoFactory.createAddSizeToRequestDto(1L, productSizeDtoList)).thenReturn(addSizeToProductRequestDto);
        when(productSizeService.addSizeToProduct(addSizeToProductRequestDto)).thenReturn(expectedProduct);
        when(modelMapper.convertToResponse(ArgumentMatchers.<Class<ProductEntity>>any(), ArgumentMatchers.<Class<ProductResponseDto>>any())).thenReturn(resultProductDto);
    }

    @Test
    public void createProduct_ShouldReturnProductResponseDto() {
        when(createProductRequestDTO.getGenderId()).thenReturn(1L);
        when(createProductRequestDTO.getSportId()).thenReturn(2L);
        when(createProductRequestDTO.getCategoryIds()).thenReturn(categoryIds);
        when(createProductRequestDTO.getTechnologyIds()).thenReturn(technologyIds);
        when(createProductRequestDTO.getProductSizeDtoList()).thenReturn(productSizeDtoList);

        when(genderCrudService.findById(1L)).thenReturn(genderEntity);
        when(sportCrudService.findById(1L)).thenReturn(sportEntity);
        when(categoryCrudService.findByIds(categoryIds)).thenReturn(categoryEntities);
        when(technologyService.findByIds(technologyIds)).thenReturn(technologyEntities);

        when(modelMapper.convertToEntity(createProductRequestDTO, ProductEntity.class)).thenReturn(expectedProduct);
        when(productRepository.save(any())).thenReturn(expectedProduct);

        when(createProductRequestDTO.getProductSizeDtoList()).thenReturn(productSizeDtoList);
        when(addSizeToProductRequestDtoFactory.createAddSizeToRequestDto(1L, productSizeDtoList)).thenReturn(addSizeToProductRequestDto);
        when(productSizeService.addSizeToProduct(addSizeToProductRequestDto)).thenReturn(expectedProduct);
        when(modelMapper.convertToResponse(ArgumentMatchers.<Class<ProductEntity>>any(), ArgumentMatchers.<Class<ProductResponseDto>>any())).thenReturn(resultProductDto);

        ProductResponseDto result = productCrudServiceImpl.createProduct(createProductRequestDTO);

        ArgumentCaptor<ProductEntity> productCaptor = ArgumentCaptor.forClass(ProductEntity.class);
        verify(productRepository).save(productCaptor.capture());
        ProductEntity savedProduct = productCaptor.getValue();

        ArgumentCaptor<ProductEntity> mappedProductCaptor = ArgumentCaptor.forClass(ProductEntity.class);
        verify(modelMapper, times(1)).convertToResponse(mappedProductCaptor.capture(), ArgumentMatchers.<Class<ProductResponseDto>>any());
        ProductEntity mappedProduct = mappedProductCaptor.getValue();

        assertEquals(savedProduct, expectedProduct);
        assertEquals(mappedProduct, expectedProduct);
        assertThat(mappedProduct.getId(), is(expectedProduct.getId()));
        assertThat(result, is(resultProductDto));
    }

    @Test
    public void updateProduct_ShouldReturnProductResponseDto_WhenDataValid() {
        UpdateProductRequestDto updateProductRequestDto = mock(UpdateProductRequestDto.class);
        initProduct = mock(ProductEntity.class);
        expectedProduct = mock(ProductEntity.class);
        ProductCrudServiceImpl productCrudServiceSpy = spy(productCrudServiceImpl);

        when(updateProductRequestDto.getProductId()).thenReturn(1L);
        doReturn(initProduct).when(productCrudServiceSpy).findById(nullable(Long.class));
        when(updateProductRequestDto.getCategoryIds()).thenReturn(categoryIds);
        doNothing().when(productCrudServiceSpy).updateProductCategory(initProduct, categoryIds);
        when(updateProductRequestDto.getTechnologyIds()).thenReturn(technologyIds);
        doNothing().when(productCrudServiceSpy).updateProductTechnology(initProduct, technologyIds);
        when(categoryIds.isEmpty()).thenReturn(false);
        when(technologyIds.isEmpty()).thenReturn(false);
        when(productSizeDtoList.isEmpty()).thenReturn(false);
        when(updateProductRequestDto.getProductSizeDtoList()).thenReturn(productSizeDtoList);
        doNothing().when(productCrudServiceSpy).updateProductSize(initProduct, productSizeDtoList);
        when(updateProductRequestDto.getGenderId()).thenReturn(1L);
        when(updateProductRequestDto.getSportId()).thenReturn(2L);
        when(genderCrudService.findById(1L)).thenReturn(genderEntity);
        when(sportCrudService.findById(2L)).thenReturn(sportEntity);
        when(productRepository.save(initProduct)).thenReturn(expectedProduct);
        when(modelMapper.convertToResponse(expectedProduct, ProductResponseDto.class)).thenReturn(resultProductDto);

        ProductResponseDto result = productCrudServiceImpl.updateProduct(updateProductRequestDto);

        verify(modelMapper).convertToEntity(updateProductRequestDto, initProduct);
        verify(productCrudServiceSpy).updateProductCategory(initProduct, categoryIds);
        verify(productCrudServiceSpy).updateProductTechnology(initProduct, technologyIds);
        verify(productCrudServiceSpy).updateProductSize(initProduct, productSizeDtoList);
        verify(initProduct).setGender(genderEntity);
        verify(initProduct).setSport(sportEntity);

        assertThat(result, is(resultProductDto));
    }
}
