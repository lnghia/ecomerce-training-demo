package com.example.demo.services.implementations.productsize;

import com.example.demo.dto.requests.product.AddSizeToProductRequestDto;
import com.example.demo.dto.requests.product.ProductSizeDto;
import com.example.demo.entities.ProductEntity;
import com.example.demo.entities.ProductSizeEntity;
import com.example.demo.entities.SizeEntity;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.ProductSizeRepository;
import com.example.demo.services.interfaces.productsize.ProductSizeService;
import com.example.demo.services.interfaces.size.SizeService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

        productSizeRepository.save(productSizeEntity);
    }

    @Override
    public ProductEntity addSizeToProduct(AddSizeToProductRequestDto addSizeToProductRequestDto) {
        Long productId = addSizeToProductRequestDto.getProductId();
        Optional<ProductEntity> productEntity = productRepository.findById(productId);

        if (!productEntity.isPresent()) {
            throw new ProductNotFoundException();
        }

        ProductEntity product = productEntity.get();
        Map<Long, Integer> sizes = addSizeToProductRequestDto.getProductSizeDto().stream().collect(Collectors.toMap(ProductSizeDto::getSizeId, ProductSizeDto::getNumber));
        List<SizeEntity> sizeEntities = sizeService.findByIds(sizes.keySet());
        List<ProductSizeEntity> productSizeEntities = sizeEntities.stream().map(sizeEntity -> ProductSizeEntity.builder()
                .size(sizeEntity)
                .product(product)
                .inStock(sizes.get(sizeEntity.getId()))
                .build()).collect(Collectors.toList());
        productSizeEntities = productSizeRepository.saveAll(productSizeEntities);
        product.setSizes(new HashSet<>(productSizeEntities));

        return product;
    }

    @Override
    public void updateProductSizes(ProductEntity productEntity, List<ProductSizeDto> productSizeDtoList) {
        HashMap<Long, Integer> sizeNumber = new HashMap<>();

        for (var item : productSizeDtoList) {
            sizeNumber.put(item.getSizeId(), item.getNumber());
        }
        Set<ProductSizeEntity> productSizeEntities = productEntity.getSizes().stream().map(productSizeEntity -> {
            Long id = productSizeEntity.getSize().getId();
            if (sizeNumber.containsKey(id)) {
                productSizeEntity.setInStock(sizeNumber.get(id));
            }

            return productSizeEntity;
        }).collect(Collectors.toSet());

        productEntity.setSizes(productSizeEntities);
    }
}
