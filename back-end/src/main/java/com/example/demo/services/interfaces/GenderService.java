package com.example.demo.services.interfaces;

import com.example.demo.dto.responses.GenderResponseDTO;
import com.example.demo.entities.GenderEntity;

public interface GenderService {
    GenderResponseDTO save(GenderEntity genderEntity);

    GenderEntity findById(Long id);
}
