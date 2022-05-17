package com.example.demo.services.interfaces.sport;

import com.example.demo.dto.responses.sport.SportResponseDto;
import com.example.demo.entities.SportEntity;

import java.util.List;

public interface SportDatabaseService {
//    Optional<SportEntity> findById(Long id);

    List<SportResponseDto> findAll();

    SportEntity findById(Long id);
}
