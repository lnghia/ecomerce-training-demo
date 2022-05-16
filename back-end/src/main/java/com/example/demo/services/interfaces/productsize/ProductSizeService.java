package com.example.demo.services.interfaces.productsize;

import com.example.demo.dto.requests.product.AddSizeToProductRequestDto;
import com.example.demo.dto.requests.product.ProductSizeDto;
import com.example.demo.entities.ProductEntity;

import java.util.List;

public interface ProductSizeService {
    void createProductSize(ProductEntity productEntity, Long sizeId, int inStock);

    ProductEntity addSizeToProduct(AddSizeToProductRequestDto addSizeToProductRequestDto);

    void updateProductSizes(ProductEntity productEntity, List<ProductSizeDto> productSizeDtoList);
}
