package com.example.demo.services.interfaces;

import com.example.demo.entities.CollectionEntity;

public interface CollectionCrudService {
    CollectionEntity findById(Long id);
}
