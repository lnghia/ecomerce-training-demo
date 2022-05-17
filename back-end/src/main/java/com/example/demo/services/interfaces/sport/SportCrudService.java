package com.example.demo.services.interfaces.sport;

import com.example.demo.dto.requests.sport.CreateSportRequestDto;
import com.example.demo.dto.requests.sport.UpdateSportRequestDto;
import com.example.demo.dto.responses.sport.SportResponseDto;

public interface SportCrudService {
    SportResponseDto updateSport(Long sportId, UpdateSportRequestDto requestDto);

    SportResponseDto createSport(CreateSportRequestDto requestDto);
}
