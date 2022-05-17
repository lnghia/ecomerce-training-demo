package com.example.demo.services.interfaces.gender;

import com.example.demo.dto.responses.gender.GenderResponseDto;
import com.example.demo.entities.GenderEntity;

import java.util.List;

public interface GenderDatabaseService {
    GenderEntity findById(Long id);

    GenderResponseDto save(GenderEntity genderEntity);

    List<GenderResponseDto> findAll();
}
