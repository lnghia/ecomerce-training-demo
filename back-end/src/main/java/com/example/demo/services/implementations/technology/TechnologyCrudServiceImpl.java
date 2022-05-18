package com.example.demo.services.implementations.technology;

import com.example.demo.dto.requests.technology.TechnologyCreateRequestDto;
import com.example.demo.dto.responses.technology.TechnologyResponseDto;
import com.example.demo.entities.TechnologyEntity;
import com.example.demo.exceptions.TechnologyNotFoundException;
import com.example.demo.repositories.TechnologyRepository;
import com.example.demo.services.interfaces.technology.TechnologyCrudService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class TechnologyCrudServiceImpl implements TechnologyCrudService {
    private TechnologyRepository technologyRepository;

    private ModelMapper modelMapper;

    @Autowired
    public TechnologyCrudServiceImpl(TechnologyRepository technologyRepository, ModelMapper modelMapper) {
        this.technologyRepository = technologyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TechnologyResponseDto createTechnology(TechnologyCreateRequestDto requestDto) {
        String name = requestDto.getName();
        String description = requestDto.getDescription();

        TechnologyEntity technologyEntity = TechnologyEntity.builder().name(name).description(description).build();
        technologyEntity = technologyRepository.save(technologyEntity);

        return modelMapper.map(technologyEntity, TechnologyResponseDto.class);
    }

    @Override
    public TechnologyResponseDto updateTechnology(Long technologyId, TechnologyCreateRequestDto requestDto) {
        TechnologyEntity technologyEntity = technologyRepository.findById(technologyId).orElseThrow(TechnologyNotFoundException::new);

        modelMapper.map(requestDto, technologyEntity);
        technologyEntity = technologyRepository.save(technologyEntity);

        return modelMapper.map(technologyEntity, TechnologyResponseDto.class);
    }


}
