package com.example.demo.services.interfaces;

import com.example.demo.entities.SportEntity;

import java.util.Optional;

public interface SportService {
    Optional<SportEntity> findById(Long id);
}
