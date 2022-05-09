package com.example.demo.services.interfaces.gender;

import com.example.demo.dto.requests.gender.CreateGenderRequestDto;
import com.example.demo.dto.requests.gender.DeleteGenderRequestDto;
import com.example.demo.dto.requests.gender.UpdateGenderRequestDto;
import com.example.demo.dto.responses.gender.GenderResponseDto;
import com.example.demo.entities.GenderEntity;

import java.util.List;

public interface GenderService {
    GenderResponseDto save(GenderEntity genderEntity);

    GenderEntity findById(Long id);

    List<GenderResponseDto> findAll();

    GenderResponseDto createGender(CreateGenderRequestDto requestDto);

    GenderResponseDto updateGender(UpdateGenderRequestDto requestDto);

    GenderResponseDto deleteGender(DeleteGenderRequestDto requestDto);
}
