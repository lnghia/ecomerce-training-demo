package com.example.demo.services.interfaces;

import com.example.demo.entities.UpperEntity;

public interface UpperCrudService {
    UpperEntity findById(Long id);
}
