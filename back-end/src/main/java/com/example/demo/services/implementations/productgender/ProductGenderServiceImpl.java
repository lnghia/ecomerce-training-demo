package com.example.demo.services.implementations.productgender;

import com.example.demo.entities.GenderEntity;
import com.example.demo.entities.ProductEntity;
import com.example.demo.services.interfaces.gender.GenderCrudService;
import com.example.demo.services.interfaces.productgender.ProductGenderService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class ProductGenderServiceImpl implements ProductGenderService {
    private GenderCrudService genderCrudService;

    @Autowired
    public ProductGenderServiceImpl(GenderCrudService genderCrudService) {
        this.genderCrudService = genderCrudService;
    }

    @Override
    public void updateProductGender(ProductEntity productEntity, Long genderId) {
        GenderEntity genderEntity = genderCrudService.findById(genderId);
        productEntity.setGender(genderEntity);
    }
}
