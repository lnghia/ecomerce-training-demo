package com.example.demo.services.implementations.productsport;

import com.example.demo.entities.ProductEntity;
import com.example.demo.entities.SportEntity;
import com.example.demo.services.interfaces.productsport.ProductSportService;
import com.example.demo.services.interfaces.sport.SportCrudService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class ProductSportServiceImpl implements ProductSportService {
    private SportCrudService sportCrudService;

    @Autowired
    public ProductSportServiceImpl(SportCrudService sportCrudService) {
        this.sportCrudService = sportCrudService;
    }

    @Override
    public void updateProductSport(ProductEntity productEntity, Long sportId) {
        SportEntity sportEntity = sportCrudService.findById(sportId);
        productEntity.setSport(sportEntity);
    }
}
