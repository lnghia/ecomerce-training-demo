package com.example.demo.services.implementations.productsport;

import com.example.demo.entities.ProductEntity;
import com.example.demo.entities.SportEntity;
import com.example.demo.services.interfaces.productsport.ProductSportService;
import com.example.demo.services.interfaces.sport.SportDatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductSportServiceImpl implements ProductSportService {
  private final SportDatabaseService SportDatabaseService;

  @Override
  public void updateProductSport(ProductEntity productEntity, Long sportId) {
    SportEntity sportEntity = SportDatabaseService.findById(sportId);
    productEntity.setSport(sportEntity);
  }
}
