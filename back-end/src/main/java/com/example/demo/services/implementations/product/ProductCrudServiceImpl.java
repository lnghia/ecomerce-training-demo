package com.example.demo.services.implementations.product;

import com.example.demo.dto.requests.product.AddSizeToProductRequestDto;
import com.example.demo.dto.requests.product.CreateProductRequestDto;
import com.example.demo.dto.requests.product.ProductSizeDto;
import com.example.demo.dto.requests.product.UpdateProductRequestDto;
import com.example.demo.dto.responses.product.ProductResponseDto;
import com.example.demo.entities.*;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.interfaces.category.CategoryCrudService;
import com.example.demo.services.interfaces.gender.GenderCrudService;
import com.example.demo.services.interfaces.product.ProductCrudService;
import com.example.demo.services.interfaces.product.ProductSizeService;
import com.example.demo.services.interfaces.sport.SportCrudService;
import com.example.demo.services.interfaces.technology.TechnologyService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class ProductCrudServiceImpl implements ProductCrudService {
    private ModelMapper modelMapper;

    private GenderCrudService genderCrudService;

    private SportCrudService sportCrudService;

    private CategoryCrudService categoryCrudService;

    private ProductRepository productRepository;

    private TechnologyService technologyService;

    private ProductSizeService productSizeService;

    @Autowired
    public ProductCrudServiceImpl(ModelMapper modelMapper,
                                  GenderCrudService genderCrudService,
                                  SportCrudService sportCrudService,
                                  CategoryCrudService categoryCrudService,
                                  ProductRepository productRepository,
                                  TechnologyService technologyService,
                                  ProductSizeService productSizeService) {
        this.modelMapper = modelMapper;
        this.genderCrudService = genderCrudService;
        this.sportCrudService = sportCrudService;
        this.categoryCrudService = categoryCrudService;
        this.productRepository = productRepository;
        this.technologyService = technologyService;
        this.productSizeService = productSizeService;
    }

    @Override
    public ProductResponseDto createProduct(CreateProductRequestDto createProductRequestDTO) {
        Long genderId = createProductRequestDTO.getGenderId();
        Long sportId = createProductRequestDTO.getSportId();
        List<Long> categoryIds = createProductRequestDTO.getCategoryIds();
        List<Long> technologyIds = createProductRequestDTO.getTechnologyIds();

        GenderEntity genderEntity = genderCrudService.findById(genderId);
        SportEntity sportEntity = sportCrudService.findById(sportId);
        List<CategoryEntity> categoryEntities = categoryCrudService.findByIds(categoryIds);
        List<TechnologyEntity> technologyEntities = technologyService.findByIds(technologyIds);


        ProductEntity productEntity = ProductEntity.builder()
                .categories(categoryEntities.stream().collect(Collectors.toSet()))
                .technologies(technologyEntities.stream().collect(Collectors.toSet()))
                .gender(genderEntity)
                .sport(sportEntity)
                .price(createProductRequestDTO.getPrice())
                .year(createProductRequestDTO.getYear())
                .name(createProductRequestDTO.getName())
                .description(createProductRequestDTO.getDescription())
                .thumbnail(createProductRequestDTO.getThumbnail())
                .build();

        productEntity = productRepository.save(productEntity);

        AddSizeToProductRequestDto requestDto = AddSizeToProductRequestDto.builder().productId(productEntity.getId()).productSizeDto(createProductRequestDTO.getProductSizeDtoList()).build();
        productEntity = productSizeService.addSizeToProduct(requestDto);

        return modelMapper.map(productEntity, ProductResponseDto.class);
    }


    @Override
    public ProductResponseDto updateProduct(UpdateProductRequestDto updateProductRequestDto) {
        ProductEntity productEntity = findById(updateProductRequestDto.getProductId());

        modelMapper.map(updateProductRequestDto, productEntity);

        List<Long> categoryIds = updateProductRequestDto.getCategoryIds();
        if (categoryIds != null && !categoryIds.isEmpty()) {
            updateProductCategory(productEntity, updateProductRequestDto.getCategoryIds());
        }

        List<Long> technologyIds = updateProductRequestDto.getTechnologyIds();
        if (technologyIds != null && !technologyIds.isEmpty()) {
            updateProductTechnology(productEntity, updateProductRequestDto.getTechnologyIds());
        }

        List<ProductSizeDto> productSizeDtoList = updateProductRequestDto.getProductSizeDtoList();
        if (productSizeDtoList != null && !productSizeDtoList.isEmpty()) {
            updateProductSize(productEntity, updateProductRequestDto.getProductSizeDtoList());
        }

        if (updateProductRequestDto.getGenderId() != null) {
            GenderEntity newGender = genderCrudService.findById(updateProductRequestDto.getGenderId());
            productEntity.setGender(newGender);
        }

        if (updateProductRequestDto.getSportId() != null) {
            SportEntity newSport = sportCrudService.findById(updateProductRequestDto.getSportId());
            productEntity.setSport(newSport);
        }

        productEntity = productRepository.save(productEntity);

        return modelMapper.map(productEntity, ProductResponseDto.class);
    }

    @Override
    public ProductEntity findById(Long id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);

        if (!productEntity.isPresent()) {
            throw new ProductNotFoundException();
        }

        return productEntity.get();
    }

    @Override
    public Boolean deleteProduct(Long id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);

        if (!productEntity.isPresent()) {
            throw new ProductNotFoundException();
        }

        productRepository.deleteById(productEntity.get().getId());

        return true;
    }

    @Override
    public ProductEntity saveProduct(ProductEntity product) {
        return productRepository.save(product);
    }

    @Override
    public void updateProductCategory(ProductEntity product, List<Long> categoryIds) {
        List<CategoryEntity> categoryEntities = categoryCrudService.findByIds(categoryIds);

        product.getCategories().clear();
        product.setCategories(categoryEntities.stream().collect(Collectors.toSet()));
    }

    @Override
    public void updateProductTechnology(ProductEntity product, List<Long> technologyIds) {
        List<TechnologyEntity> technologyEntities = technologyService.findByIds(technologyIds);

        product.getTechnologies().clear();
        product.setTechnologies(technologyEntities.stream().collect(Collectors.toSet()));
    }

    @Override
    public void updateProductSize(ProductEntity product, List<ProductSizeDto> productSizeDtoList) {
        HashMap<Long, Integer> sizeNumber = new HashMap<>();

        for (var item : productSizeDtoList) {
            sizeNumber.put(item.getSizeId(), item.getNumber());
        }
        Set<ProductSizeEntity> productSizeEntities = product.getSizes().stream().map(productSizeEntity -> {
            Long id = productSizeEntity.getSize().getId();
            if (sizeNumber.containsKey(id)) {
                productSizeEntity.setInStock(sizeNumber.get(id));
            }

            return productSizeEntity;
        }).collect(Collectors.toSet());

        product.setSizes(productSizeEntities);
    }
}
