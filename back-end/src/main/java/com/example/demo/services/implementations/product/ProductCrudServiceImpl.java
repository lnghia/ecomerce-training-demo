package com.example.demo.services.implementations.product;

import com.example.demo.configurations.modelmapper.converters.CommonConverter;
import com.example.demo.dto.requests.product.AddSizeToProductRequestDto;
import com.example.demo.dto.requests.product.CreateProductRequestDto;
import com.example.demo.dto.requests.product.ProductSizeDto;
import com.example.demo.dto.requests.product.UpdateProductRequestDto;
import com.example.demo.dto.responses.product.ProductResponseDto;
import com.example.demo.entities.ProductEntity;
import com.example.demo.entities.factories.addsizetoproduct.AddSizeToProductRequestDtoFactory;
import com.example.demo.services.interfaces.product.ProductCrudService;
import com.example.demo.services.interfaces.product.ProductDatabaseService;
import com.example.demo.services.interfaces.productcategory.ProductCategoryService;
import com.example.demo.services.interfaces.productgender.ProductGenderService;
import com.example.demo.services.interfaces.productsize.ProductSizeService;
import com.example.demo.services.interfaces.productsport.ProductSportService;
import com.example.demo.services.interfaces.producttechnology.ProductTechnologyService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class ProductCrudServiceImpl implements ProductCrudService {
    private CommonConverter modelMapper;

    private ProductGenderService productGenderService;

    private ProductSportService productSportService;

    private ProductCategoryService productCategoryService;

    private ProductDatabaseService productDatabaseService;

    private ProductTechnologyService productTechnologyService;

    private ProductSizeService productSizeService;

    private AddSizeToProductRequestDtoFactory addSizeToProductRequestDtoFactory;

    @Autowired
    public ProductCrudServiceImpl(CommonConverter modelMapper,
                                  ProductGenderService productGenderService,
                                  ProductSportService productSportService,
                                  ProductCategoryService productCategoryService,
                                  ProductDatabaseService productDatabaseService,
                                  ProductTechnologyService productTechnologyService,
                                  ProductSizeService productSizeService,
                                  AddSizeToProductRequestDtoFactory addSizeToProductRequestDtoFactory) {
        this.modelMapper = modelMapper;
        this.productGenderService = productGenderService;
        this.productSportService = productSportService;
        this.productCategoryService = productCategoryService;
        this.productDatabaseService = productDatabaseService;
        this.productTechnologyService = productTechnologyService;
        this.productSizeService = productSizeService;
        this.addSizeToProductRequestDtoFactory = addSizeToProductRequestDtoFactory;
    }

    @Override
    public ProductResponseDto createProduct(CreateProductRequestDto createProductRequestDTO) {
        Long genderId = createProductRequestDTO.getGenderId();
        Long sportId = createProductRequestDTO.getSportId();
        List<Long> categoryIds = createProductRequestDTO.getCategoryIds();
        List<Long> technologyIds = createProductRequestDTO.getTechnologyIds();

        ProductEntity productEntity = modelMapper.convertToEntity(createProductRequestDTO, ProductEntity.class);

        productTechnologyService.updateProductTechnologies(productEntity, technologyIds);

        productCategoryService.updateProductCategories(productEntity, categoryIds);

        productGenderService.updateProductGender(productEntity, genderId);

        productSportService.updateProductSport(productEntity, sportId);

        productEntity = productDatabaseService.saveProduct(productEntity);

        List<ProductSizeDto> productSizeDtoList = createProductRequestDTO.getProductSizeDtoList();
        Long productId = productEntity.getId();

        AddSizeToProductRequestDto requestDto = addSizeToProductRequestDtoFactory.createAddSizeToRequestDto(productId, productSizeDtoList);
        productEntity = productSizeService.addSizeToProduct(requestDto);
        ProductResponseDto responseDto = modelMapper.convertToResponse(productEntity, ProductResponseDto.class);

        return responseDto;
    }


    @Override
    public ProductResponseDto updateProduct(UpdateProductRequestDto updateProductRequestDto) {
        Long productId = updateProductRequestDto.getProductId();
        ProductEntity productEntity = productDatabaseService.findById(productId);

        modelMapper.convertToEntity(updateProductRequestDto, productEntity);

        List<Long> categoryIds = updateProductRequestDto.getCategoryIds();
        if (categoryIds != null && !categoryIds.isEmpty()) {
            productCategoryService.updateProductCategories(productEntity, categoryIds);
        }

        List<Long> technologyIds = updateProductRequestDto.getTechnologyIds();
        if (technologyIds != null && !technologyIds.isEmpty()) {
            productTechnologyService.updateProductTechnologies(productEntity, technologyIds);
        }

        List<ProductSizeDto> productSizeDtoList = updateProductRequestDto.getProductSizeDtoList();
        if (productSizeDtoList != null && !productSizeDtoList.isEmpty()) {
            productSizeService.updateProductSizes(productEntity, productSizeDtoList);
        }

        Long genderId = updateProductRequestDto.getGenderId();
        if (updateProductRequestDto.getGenderId() != null) {
            productGenderService.updateProductGender(productEntity, genderId);
        }

        Long sportId = updateProductRequestDto.getSportId();
        if (updateProductRequestDto.getSportId() != null) {
            productSportService.updateProductSport(productEntity, sportId);
        }

        productEntity = productDatabaseService.saveProduct(productEntity);

        return modelMapper.convertToResponse(productEntity, ProductResponseDto.class);
    }

    @Override
    public Boolean deleteProduct(Long id) {
        return productDatabaseService.deleteProduct(id);
    }
}
