package com.example.demo.services.implementations.product;

import com.example.demo.dto.requests.product.CreateProductRequestDto;
import com.example.demo.dto.requests.product.UpdateProductRequestDto;
import com.example.demo.dto.responses.product.ProductResponseDto;
import com.example.demo.entities.*;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.interfaces.category.CategoryCrudService;
import com.example.demo.services.interfaces.gender.GenderCrudService;
import com.example.demo.services.interfaces.product.ProductCrudService;
import com.example.demo.services.interfaces.sport.SportCrudService;
import com.example.demo.services.interfaces.technology.TechnologyService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    public ProductCrudServiceImpl(ModelMapper modelMapper,
                                  GenderCrudService genderCrudService,
                                  SportCrudService sportCrudService,
                                  CategoryCrudService categoryCrudService,
                                  ProductRepository productRepository,
                                  TechnologyService technologyService) {
        this.modelMapper = modelMapper;
        this.genderCrudService = genderCrudService;
        this.sportCrudService = sportCrudService;
        this.categoryCrudService = categoryCrudService;
        this.productRepository = productRepository;
        this.technologyService = technologyService;
    }

    @Override
    public ProductResponseDto createProduct(CreateProductRequestDto createProductRequestDTO) {
        Long genderId = createProductRequestDTO.getGenderId();
        Long sportId = createProductRequestDTO.getSportId();
        Set<Long> categoryIds = createProductRequestDTO.getCategoryIds();
        Set<Long> technologyIds = createProductRequestDTO.getTechnologyIds();

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
                .build();

        productEntity = productRepository.save(productEntity);

        return modelMapper.map(productEntity, ProductResponseDto.class);
    }

    @Override
    public ProductResponseDto updateProduct(UpdateProductRequestDto updateProductRequestDto) {
        ProductEntity productEntity = findById(updateProductRequestDto.getProductId());

        modelMapper.map(updateProductRequestDto, productEntity);
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
}
