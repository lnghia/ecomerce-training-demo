package com.example.demo.services.implementations.producttechnology;

import com.example.demo.entities.ProductEntity;
import com.example.demo.entities.TechnologyEntity;
import com.example.demo.services.interfaces.producttechnology.ProductTechnologyService;
import com.example.demo.services.interfaces.technology.TechnologyDatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductTechnologyServiceImpl implements ProductTechnologyService {
  private final TechnologyDatabaseService technologyDatabaseService;

  @Override
  public void updateProductTechnologies(ProductEntity productEntity, List<Long> technologyIds) {
    List<TechnologyEntity> technologyEntities = technologyDatabaseService.findByIds(technologyIds);

    productEntity.getTechnologies().clear();
    productEntity.setTechnologies(new HashSet<>(technologyEntities));
  }
}
