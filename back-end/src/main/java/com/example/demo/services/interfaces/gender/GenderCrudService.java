package com.example.demo.services.interfaces.gender;

import com.example.demo.entities.GenderEntity;

public interface GenderCrudService {
    GenderEntity findById(Long id);
}
