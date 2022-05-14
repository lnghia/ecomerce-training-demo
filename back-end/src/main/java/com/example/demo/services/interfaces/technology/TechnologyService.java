package com.example.demo.services.interfaces.technology;


import com.example.demo.dto.requests.technology.TechnologyCreateRequestDto;
import com.example.demo.dto.responses.technology.TechnologyResponseDto;
import com.example.demo.entities.TechnologyEntity;

import java.util.List;

public interface TechnologyService {
    List<TechnologyEntity> findByIds(List<Long> ids);

    List<TechnologyResponseDto> findAll();

    TechnologyEntity findById(Long id);

    TechnologyResponseDto createTechnology(TechnologyCreateRequestDto requestDto);

    TechnologyResponseDto updateTechnology(Long technologyId, TechnologyCreateRequestDto requestDto);
}
