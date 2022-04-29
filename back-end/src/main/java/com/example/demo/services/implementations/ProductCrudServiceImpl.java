package com.example.demo.services.implementations;

import com.example.demo.dto.requests.CreateProductRequestDto;
import com.example.demo.dto.responses.ProductResponseDto;
import com.example.demo.entities.*;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.interfaces.*;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class ProductCrudServiceImpl implements ProductCrudService {
    private ModelMapper modelMapper;

    private GenderCrudService genderCrudService;

    private SportCrudService sportCrudService;

    private UpperCrudService upperCrudService;

    private MidsoleCrudService midsoleCrudService;

    private CollectionCrudService collectionCrudService;

    private CategoryCrudService categoryCrudService;

    private ProductRepository productRepository;

    @Autowired
    public ProductCrudServiceImpl(ModelMapper modelMapper,
                                  GenderCrudService genderCrudService,
                                  SportCrudService sportCrudService,
                                  UpperCrudService upperCrudService,
                                  MidsoleCrudService midsoleCrudService,
                                  CollectionCrudService collectionCrudService,
                                  CategoryCrudService categoryCrudService,
                                  ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.genderCrudService = genderCrudService;
        this.sportCrudService = sportCrudService;
        this.upperCrudService = upperCrudService;
        this.midsoleCrudService = midsoleCrudService;
        this.collectionCrudService = collectionCrudService;
        this.categoryCrudService = categoryCrudService;
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto createProduct(CreateProductRequestDto createProductRequestDTO) {
        Long genderId = createProductRequestDTO.getGenderId();
        Long sportId = createProductRequestDTO.getSportId();
        Long upperId = createProductRequestDTO.getUpperId();
        Long midsoleId = createProductRequestDTO.getMidsoleId();
        Long collectionId = createProductRequestDTO.getCollectionId();
        Long categoryId = createProductRequestDTO.getCategoryId();
        CollectionEntity collectionEntity = null;

        GenderEntity genderEntity = genderCrudService.findById(genderId);
        SportEntity sportEntity = sportCrudService.findById(sportId);
        UpperEntity upperEntity = upperCrudService.findById(upperId);
        MidsoleEntity midsoleEntity = midsoleCrudService.findById(midsoleId);
        if (collectionId != null) {
            collectionEntity = collectionCrudService.findById(collectionId);
        }
        CategoryEntity categoryEntity = categoryCrudService.findById(categoryId);

        ProductEntity productEntity = ProductEntity.builder()
                .collection(collectionEntity)
                .gender(genderEntity)
                .midsole(midsoleEntity)
                .sport(sportEntity)
                .price(createProductRequestDTO.getPrice())
                .year(createProductRequestDTO.getYear())
                .upper(upperEntity)
                .category(categoryEntity)
                .name(createProductRequestDTO.getName())
                .description(createProductRequestDTO.getDescription())
                .build();

        productEntity = productRepository.save(productEntity);

        return modelMapper.map(productEntity, ProductResponseDto.class);
    }
}
