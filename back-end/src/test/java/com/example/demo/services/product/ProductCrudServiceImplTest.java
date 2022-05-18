package com.example.demo.services.product;

import com.example.demo.configurations.modelmapper.converters.CommonConverter;
import com.example.demo.dto.requests.product.AddSizeToProductRequestDto;
import com.example.demo.dto.requests.product.CreateProductRequestDto;
import com.example.demo.dto.requests.product.ProductSizeDto;
import com.example.demo.dto.requests.product.UpdateProductRequestDto;
import com.example.demo.dto.responses.product.ProductResponseDto;
import com.example.demo.entities.CategoryEntity;
import com.example.demo.entities.GenderEntity;
import com.example.demo.entities.ProductEntity;
import com.example.demo.entities.SportEntity;
import com.example.demo.entities.factories.addsizetoproduct.AddSizeToProductRequestDtoFactory;
import com.example.demo.services.implementations.product.ProductCrudServiceImpl;
import com.example.demo.services.interfaces.product.ProductDatabaseService;
import com.example.demo.services.interfaces.productcategory.ProductCategoryService;
import com.example.demo.services.interfaces.productgender.ProductGenderService;
import com.example.demo.services.interfaces.productsize.ProductSizeService;
import com.example.demo.services.interfaces.productsport.ProductSportService;
import com.example.demo.services.interfaces.producttechnology.ProductTechnologyService;
import com.example.demo.utilities.converter.ConverterUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

public class ProductCrudServiceImplTest {
    CommonConverter modelMapper;
    ProductCrudServiceImpl productCrudServiceImpl;
    ProductGenderService productGenderService;
    ProductSportService productSportService;
    ProductCategoryService productCategoryService;
    ProductDatabaseService productDatabaseService;
    ProductTechnologyService productTechnologyService;
    ProductSizeService productSizeService;
    CreateProductRequestDto createProductRequestDTO;
    ConverterUtil converterUtil;
    List<Long> categoryIds;
    List<Long> technologyIds;
    GenderEntity genderEntity;
    SportEntity sportEntity;
    List<CategoryEntity> categoryEntities;
    List<ProductSizeDto> productSizeDtoList;
    ProductEntity initProduct;
    ProductEntity expectedProduct;
    AddSizeToProductRequestDto addSizeToProductRequestDto;
    AddSizeToProductRequestDtoFactory addSizeToProductRequestDtoFactory;
    ProductResponseDto resultProductDto;
    UpdateProductRequestDto updateProductRequestDto;

    @BeforeEach
    public void beforeEach() {
        modelMapper = mock(CommonConverter.class);
        productGenderService = mock(ProductGenderService.class);
        productSportService = mock(ProductSportService.class);
        productCategoryService = mock(ProductCategoryService.class);
        productDatabaseService = mock(ProductDatabaseService.class);
        productTechnologyService = mock(ProductTechnologyService.class);
        productSizeService = mock(ProductSizeService.class);
        converterUtil = mock(ConverterUtil.class);
        addSizeToProductRequestDto = mock(AddSizeToProductRequestDto.class);
        addSizeToProductRequestDtoFactory = mock(AddSizeToProductRequestDtoFactory.class);
        productCrudServiceImpl = new ProductCrudServiceImpl(
                modelMapper,
                productGenderService,
                productSportService,
                productCategoryService,
                productDatabaseService,
                productTechnologyService,
                productSizeService,
                addSizeToProductRequestDtoFactory
        );

        createProductRequestDTO = mock(CreateProductRequestDto.class);
        genderEntity = mock(GenderEntity.class);
        sportEntity = mock(SportEntity.class);
        categoryIds = mock(List.class);
        technologyIds = mock(List.class);
        categoryEntities = mock(List.class);
        expectedProduct = mock(ProductEntity.class);
        productSizeDtoList = mock(List.class);
        addSizeToProductRequestDto = mock(AddSizeToProductRequestDto.class);
        resultProductDto = mock(ProductResponseDto.class);

        updateProductRequestDto = mock(UpdateProductRequestDto.class);
        initProduct = mock(ProductEntity.class);
        expectedProduct = mock(ProductEntity.class);

        when(updateProductRequestDto.getProductId()).thenReturn(1L);
        when(productDatabaseService.findById(1L)).thenReturn(initProduct);

        when(createProductRequestDTO.getGenderId()).thenReturn(1L);
        when(createProductRequestDTO.getSportId()).thenReturn(2L);
        when(createProductRequestDTO.getCategoryIds()).thenReturn(categoryIds);
        when(createProductRequestDTO.getTechnologyIds()).thenReturn(technologyIds);
        when(createProductRequestDTO.getProductSizeDtoList()).thenReturn(productSizeDtoList);

        when(updateProductRequestDto.getCategoryIds()).thenReturn(categoryIds);
        when(updateProductRequestDto.getTechnologyIds()).thenReturn(technologyIds);
        when(updateProductRequestDto.getProductSizeDtoList()).thenReturn(productSizeDtoList);
        when(updateProductRequestDto.getGenderId()).thenReturn(1L);
        when(updateProductRequestDto.getSportId()).thenReturn(2L);

        when(categoryIds.isEmpty()).thenReturn(false);
        when(technologyIds.isEmpty()).thenReturn(false);
        when(productSizeDtoList.isEmpty()).thenReturn(false);

        when(productDatabaseService.saveProduct(initProduct)).thenReturn(expectedProduct);
        when(modelMapper.convertToResponse(expectedProduct, ProductResponseDto.class)).thenReturn(resultProductDto);
    }

    @Test
    public void createProduct_ShouldReturnProductResponseDto() {
        when(modelMapper.convertToEntity(createProductRequestDTO, ProductEntity.class)).thenReturn(expectedProduct);

        when(productDatabaseService.saveProduct(expectedProduct)).thenReturn(expectedProduct);

        when(createProductRequestDTO.getProductSizeDtoList()).thenReturn(productSizeDtoList);
        when(expectedProduct.getId()).thenReturn(1L);

        when(addSizeToProductRequestDtoFactory.createAddSizeToRequestDto(1L, productSizeDtoList)).thenReturn(addSizeToProductRequestDto);
        when(productSizeService.addSizeToProduct(addSizeToProductRequestDto)).thenReturn(expectedProduct);
        when(modelMapper.convertToResponse(expectedProduct, ProductResponseDto.class)).thenReturn(resultProductDto);

        ProductResponseDto result = productCrudServiceImpl.createProduct(createProductRequestDTO);

        verify(productTechnologyService).updateProductTechnologies(expectedProduct, technologyIds);
        verify(productCategoryService).updateProductCategories(expectedProduct, categoryIds);
        verify(productGenderService).updateProductGender(expectedProduct, 1L);
        verify(productSportService).updateProductSport(expectedProduct, 2L);

        assertThat(result, is(resultProductDto));
    }

    @Test
    public void updateProduct_ShouldReturnProductResponseDto_WhenDataValid() {
        ProductResponseDto result = productCrudServiceImpl.updateProduct(updateProductRequestDto);

        verify(modelMapper).convertToEntity(updateProductRequestDto, initProduct);
        verify(productCategoryService).updateProductCategories(initProduct, categoryIds);
        verify(productTechnologyService).updateProductTechnologies(initProduct, technologyIds);
        verify(productSizeService).updateProductSizes(initProduct, productSizeDtoList);
        verify(productGenderService).updateProductGender(initProduct, 1L);
        verify(productSportService).updateProductSport(initProduct, 2L);

        assertThat(result, is(resultProductDto));
    }

    @Test
    public void updateProduct_ShouldShouldNotUpdateCategory_WhenCategoryIdsNull() {
        when(updateProductRequestDto.getCategoryIds()).thenReturn(null);

        ProductResponseDto result = productCrudServiceImpl.updateProduct(updateProductRequestDto);

        verify(modelMapper).convertToEntity(updateProductRequestDto, initProduct);
        verify(productCategoryService, times(0)).updateProductCategories(initProduct, categoryIds);
        verify(productTechnologyService).updateProductTechnologies(initProduct, technologyIds);
        verify(productSizeService).updateProductSizes(initProduct, productSizeDtoList);
        verify(productGenderService).updateProductGender(initProduct, 1L);
        verify(productSportService).updateProductSport(initProduct, 2L);

        assertThat(result, is(resultProductDto));
    }

    @Test
    public void updateProduct_ShouldShouldNotUpdateCategory_WhenCategoryIdsEmpty() {
        when(updateProductRequestDto.getCategoryIds()).thenReturn(List.of());

        ProductResponseDto result = productCrudServiceImpl.updateProduct(updateProductRequestDto);

        verify(modelMapper).convertToEntity(updateProductRequestDto, initProduct);
        verify(productCategoryService, times(0)).updateProductCategories(initProduct, categoryIds);
        verify(productTechnologyService).updateProductTechnologies(initProduct, technologyIds);
        verify(productSizeService).updateProductSizes(initProduct, productSizeDtoList);
        verify(productGenderService).updateProductGender(initProduct, 1L);
        verify(productSportService).updateProductSport(initProduct, 2L);

        assertThat(result, is(resultProductDto));
    }

    @Test
    public void updateProduct_ShouldShouldNotUpdateTechnology_WhenTechnologyIdsNull() {
        when(updateProductRequestDto.getTechnologyIds()).thenReturn(null);

        ProductResponseDto result = productCrudServiceImpl.updateProduct(updateProductRequestDto);

        verify(modelMapper).convertToEntity(updateProductRequestDto, initProduct);
        verify(productCategoryService).updateProductCategories(initProduct, categoryIds);
        verify(productTechnologyService, times(0)).updateProductTechnologies(initProduct, technologyIds);
        verify(productSizeService).updateProductSizes(initProduct, productSizeDtoList);
        verify(productGenderService).updateProductGender(initProduct, 1L);
        verify(productSportService).updateProductSport(initProduct, 2L);

        assertThat(result, is(resultProductDto));
    }

    @Test
    public void updateProduct_ShouldShouldNotUpdateTechnology_WhenTechnologyIdsEmpty() {
        when(updateProductRequestDto.getTechnologyIds()).thenReturn(List.of());

        ProductResponseDto result = productCrudServiceImpl.updateProduct(updateProductRequestDto);

        verify(modelMapper).convertToEntity(updateProductRequestDto, initProduct);
        verify(productCategoryService).updateProductCategories(initProduct, categoryIds);
        verify(productTechnologyService, times(0)).updateProductTechnologies(initProduct, technologyIds);
        verify(productSizeService).updateProductSizes(initProduct, productSizeDtoList);
        verify(productGenderService).updateProductGender(initProduct, 1L);
        verify(productSportService).updateProductSport(initProduct, 2L);

        assertThat(result, is(resultProductDto));
    }

    @Test
    public void updateProduct_ShouldShouldNotUpdateSizes_WhenProductSizeDtoListNull() {
        when(updateProductRequestDto.getProductSizeDtoList()).thenReturn(null);

        ProductResponseDto result = productCrudServiceImpl.updateProduct(updateProductRequestDto);

        verify(modelMapper).convertToEntity(updateProductRequestDto, initProduct);
        verify(productCategoryService).updateProductCategories(initProduct, categoryIds);
        verify(productTechnologyService).updateProductTechnologies(initProduct, technologyIds);
        verify(productSizeService, times(0)).updateProductSizes(initProduct, productSizeDtoList);
        verify(productGenderService).updateProductGender(initProduct, 1L);
        verify(productSportService).updateProductSport(initProduct, 2L);

        assertThat(result, is(resultProductDto));
    }

    @Test
    public void updateProduct_ShouldShouldNotUpdateSizes_WhenProductSizeDtoListEmpty() {
        when(updateProductRequestDto.getProductSizeDtoList()).thenReturn(List.of());

        ProductResponseDto result = productCrudServiceImpl.updateProduct(updateProductRequestDto);

        verify(modelMapper).convertToEntity(updateProductRequestDto, initProduct);
        verify(productCategoryService).updateProductCategories(initProduct, categoryIds);
        verify(productTechnologyService).updateProductTechnologies(initProduct, technologyIds);
        verify(productSizeService, times(0)).updateProductSizes(initProduct, productSizeDtoList);
        verify(productGenderService).updateProductGender(initProduct, 1L);
        verify(productSportService).updateProductSport(initProduct, 2L);

        assertThat(result, is(resultProductDto));
    }

    @Test
    public void updateProduct_ShouldShouldNotUpdateGender_WhenGenderIdNull() {
        when(updateProductRequestDto.getGenderId()).thenReturn(null);

        ProductResponseDto result = productCrudServiceImpl.updateProduct(updateProductRequestDto);

        verify(modelMapper).convertToEntity(updateProductRequestDto, initProduct);
        verify(productCategoryService).updateProductCategories(initProduct, categoryIds);
        verify(productTechnologyService).updateProductTechnologies(initProduct, technologyIds);
        verify(productSizeService).updateProductSizes(initProduct, productSizeDtoList);
        verify(productGenderService, times(0)).updateProductGender(initProduct, 1L);
        verify(productSportService).updateProductSport(initProduct, 2L);

        assertThat(result, is(resultProductDto));
    }

    @Test
    public void updateProduct_ShouldShouldNotUpdateSport_WhenSportIdNull() {
        when(updateProductRequestDto.getSportId()).thenReturn(null);

        ProductResponseDto result = productCrudServiceImpl.updateProduct(updateProductRequestDto);

        verify(modelMapper).convertToEntity(updateProductRequestDto, initProduct);
        verify(productCategoryService).updateProductCategories(initProduct, categoryIds);
        verify(productTechnologyService).updateProductTechnologies(initProduct, technologyIds);
        verify(productSizeService).updateProductSizes(initProduct, productSizeDtoList);
        verify(productGenderService).updateProductGender(initProduct, 1L);
        verify(productSportService, times(0)).updateProductSport(initProduct, 2L);

        assertThat(result, is(resultProductDto));
    }
}
