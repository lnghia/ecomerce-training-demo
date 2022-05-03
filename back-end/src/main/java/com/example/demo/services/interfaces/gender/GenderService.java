package com.example.demo.services.interfaces.gender;

import com.example.demo.dto.responses.gender.GenderResponseDto;
import com.example.demo.entities.GenderEntity;

import java.util.List;

public interface GenderService {
    GenderResponseDto save(GenderEntity genderEntity);

    GenderEntity findById(Long id);

    List<GenderResponseDto> findAll();
}
