package com.example.demo.services.interfaces.productgender;

import com.example.demo.entities.ProductEntity;

public interface ProductGenderService {
  void updateProductGender(ProductEntity productEntity, Long genderId);
}
