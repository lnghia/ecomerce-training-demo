package com.example.demo.services.interfaces;

import com.example.demo.dto.responses.GenderResponseDto;
import com.example.demo.entities.GenderEntity;

public interface GenderService {
    GenderResponseDto save(GenderEntity genderEntity);

    GenderEntity findById(Long id);
}
