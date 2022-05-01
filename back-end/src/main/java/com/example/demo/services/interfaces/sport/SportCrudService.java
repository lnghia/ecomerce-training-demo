package com.example.demo.services.interfaces.sport;

import com.example.demo.entities.SportEntity;

public interface SportCrudService {
    SportEntity findById(Long id);
}
