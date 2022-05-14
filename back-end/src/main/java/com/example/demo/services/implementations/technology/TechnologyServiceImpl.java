package com.example.demo.services.implementations.technology;

import com.example.demo.dto.requests.technology.TechnologyCreateRequestDto;
import com.example.demo.dto.responses.technology.TechnologyResponseDto;
import com.example.demo.entities.TechnologyEntity;
import com.example.demo.exceptions.TechnologyNotFoundException;
import com.example.demo.repositories.TechnologyRepository;
import com.example.demo.services.interfaces.technology.TechnologyService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class TechnologyServiceImpl implements TechnologyService {
    private TechnologyRepository technologyRepository;

    private ModelMapper modelMapper;

    @Autowired
    public TechnologyServiceImpl(TechnologyRepository technologyRepository, ModelMapper modelMapper) {
        this.technologyRepository = technologyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TechnologyEntity> findByIds(List<Long> ids) {
        List<TechnologyEntity> technologyEntities = technologyRepository.findAllById(ids);

        if (technologyEntities.size() != ids.size()) {
            throw new TechnologyNotFoundException();
        }

        return technologyEntities;
    }

    @Override
    public List<TechnologyResponseDto> findAll() {
        List<TechnologyEntity> technologyEntityList = technologyRepository.findAll();
        List<TechnologyResponseDto> result = null;

        result = technologyEntityList.stream().map(technologyEntity -> {
            return modelMapper.map(technologyEntity, TechnologyResponseDto.class);
        }).collect(Collectors.toList());

        return result;
    }

    @Override
    public TechnologyEntity findById(Long id) {
        Optional<TechnologyEntity> technologyEntity = technologyRepository.findById(id);

        if (technologyEntity.isPresent()) {
            return technologyEntity.get();
        }

        throw new TechnologyNotFoundException();
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
        TechnologyEntity technologyEntity = technologyRepository.findById(technologyId).orElseThrow(() -> new TechnologyNotFoundException());

        modelMapper.map(requestDto, technologyEntity);
        technologyEntity = technologyRepository.save(technologyEntity);

        return modelMapper.map(technologyEntity, TechnologyResponseDto.class);
    }


}
