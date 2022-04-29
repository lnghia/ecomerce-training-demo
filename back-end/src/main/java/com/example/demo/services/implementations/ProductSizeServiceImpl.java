package com.example.demo.services.implementations;

import com.example.demo.dto.requests.AddSizeToProductRequestDto;
import com.example.demo.entities.ProductEntity;
import com.example.demo.entities.ProductSizeEntity;
import com.example.demo.entities.SizeEntity;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.ProductSizeRepository;
import com.example.demo.services.interfaces.ProductSizeService;
import com.example.demo.services.interfaces.SizeService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class ProductSizeServiceImpl implements ProductSizeService {
    private SizeService sizeService;

    private ModelMapper modelMapper;

    private ProductSizeRepository productSizeRepository;

    private ProductRepository productRepository;

    @Autowired
    public ProductSizeServiceImpl(SizeService sizeService,
                                  ModelMapper modelMapper,
                                  ProductSizeRepository productSizeRepository,
                                  ProductRepository productRepository) {
        this.sizeService = sizeService;
        this.modelMapper = modelMapper;
        this.productSizeRepository = productSizeRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void createProductSize(ProductEntity productEntity, Long sizeId, int inStock) {
        SizeEntity sizeEntity = sizeService.findById(sizeId);

        ProductSizeEntity productSizeEntity = ProductSizeEntity.builder()
                .size(sizeEntity)
                .product(productEntity)
                .inStock(inStock).build();

//        try {
        productSizeRepository.save(productSizeEntity);
//        } catch (Exception exception) {
//            throw new RuntimeException();
//        }
    }

    @Override
    public boolean addSizeToProduct(AddSizeToProductRequestDto addSizeToProductRequestDto) {
        Long productId = addSizeToProductRequestDto.getProductId();
        Optional<ProductEntity> productEntity = productRepository.findById(productId);

        if (!productEntity.isPresent()) {
            throw new ProductNotFoundException();
        }

        ProductEntity product = productEntity.get();
        for (var productSizeDto : addSizeToProductRequestDto.getProductSizeDto()) {
            createProductSize(product, productSizeDto.getSizeId(), productSizeDto.getNumber());
        }

        return true;
    }

}
