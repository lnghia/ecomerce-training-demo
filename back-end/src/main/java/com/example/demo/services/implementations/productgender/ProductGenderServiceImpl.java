package com.example.demo.services.implementations.productgender;

import com.example.demo.entities.GenderEntity;
import com.example.demo.entities.ProductEntity;
import com.example.demo.services.interfaces.gender.GenderDatabaseService;
import com.example.demo.services.interfaces.productgender.ProductGenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductGenderServiceImpl implements ProductGenderService {
    private final GenderDatabaseService genderDatabaseService;

    @Override
    public void updateProductGender(ProductEntity productEntity, Long genderId) {
        GenderEntity genderEntity = genderDatabaseService.findById(genderId);
        productEntity.setGender(genderEntity);
    }
}
