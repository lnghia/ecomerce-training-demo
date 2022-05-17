package com.example.demo.services.implementations.productgender;

import com.example.demo.entities.GenderEntity;
import com.example.demo.entities.ProductEntity;
import com.example.demo.services.interfaces.gender.GenderDatabaseService;
import com.example.demo.services.interfaces.productgender.ProductGenderService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class ProductGenderServiceImpl implements ProductGenderService {
    private GenderDatabaseService genderDatabaseService;

    @Autowired
    public ProductGenderServiceImpl(GenderDatabaseService genderDatabaseService) {
        this.genderDatabaseService = genderDatabaseService;
    }

    @Override
    public void updateProductGender(ProductEntity productEntity, Long genderId) {
        GenderEntity genderEntity = genderDatabaseService.findById(genderId);
        productEntity.setGender(genderEntity);
    }
}
