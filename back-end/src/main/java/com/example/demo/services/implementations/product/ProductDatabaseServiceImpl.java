package com.example.demo.services.implementations.product;

import com.example.demo.entities.ProductEntity;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.interfaces.product.ProductDatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductDatabaseServiceImpl implements ProductDatabaseService {
  private final ProductRepository productRepository;

  @Override
  public ProductEntity findById(Long id) {
    Optional<ProductEntity> productEntity = productRepository.findById(id);

    if (productEntity.isEmpty()) {
      throw new ProductNotFoundException();
    }

    return productEntity.get();
  }

  @Override
  public ProductEntity saveProduct(ProductEntity productEntity) {
    return productRepository.save(productEntity);
  }

  @Override
  public boolean deleteProduct(Long id) {
    ProductEntity productEntity =
        productRepository
            .findById(id)
            .orElseThrow(
                () -> {
                  throw new ProductNotFoundException();
                });

    productRepository.delete(productEntity);

    return true;
  }
}
