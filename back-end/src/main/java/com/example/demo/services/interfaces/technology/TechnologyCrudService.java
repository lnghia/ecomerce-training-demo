package com.example.demo.services.interfaces.technology;

import com.example.demo.dto.requests.technology.TechnologyCreateRequestDto;
import com.example.demo.dto.responses.technology.TechnologyResponseDto;

public interface TechnologyCrudService {
  TechnologyResponseDto createTechnology(TechnologyCreateRequestDto requestDto);

  TechnologyResponseDto updateTechnology(Long technologyId, TechnologyCreateRequestDto requestDto);
}
