package com.example.demo.services.implementations.sport;

import com.example.demo.dto.requests.sport.CreateSportRequestDto;
import com.example.demo.dto.requests.sport.UpdateSportRequestDto;
import com.example.demo.dto.responses.sport.SportResponseDto;
import com.example.demo.entities.SportEntity;
import com.example.demo.exceptions.SportNotFoundException;
import com.example.demo.repositories.SportRepository;
import com.example.demo.services.interfaces.sport.SportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SportServiceImpl implements SportService {
    private ModelMapper modelMapper;

    private SportRepository sportRepository;

    @Autowired
    public SportServiceImpl(ModelMapper modelMapper, SportRepository sportRepository) {
        this.modelMapper = modelMapper;
        this.sportRepository = sportRepository;
    }

    @Override
    public Optional<SportEntity> findById(Long id) {
        return sportRepository.findById(id);
    }

    @Override
    public List<SportResponseDto> findAll() {
        List<SportEntity> sportEntities = sportRepository.findAll();
        List<SportResponseDto> result = null;

        result = sportEntities.stream().map(sportEntity -> {
            return modelMapper.map(sportEntity, SportResponseDto.class);
        }).collect(Collectors.toList());

        return result;
    }

    @Override
    public SportResponseDto updateSport(Long sportId, UpdateSportRequestDto requestDto) {
        SportEntity sportEntity = sportRepository.findById(sportId).orElseThrow(() -> new SportNotFoundException());

        modelMapper.map(requestDto, sportEntity);
        sportEntity = sportRepository.save(sportEntity);

        return modelMapper.map(sportEntity, SportResponseDto.class);
    }

    @Override
    public SportResponseDto createSport(CreateSportRequestDto requestDto) {
        SportEntity sportEntity = new SportEntity();
        modelMapper.map(requestDto, sportEntity);
        sportEntity = sportRepository.save(sportEntity);

        return modelMapper.map(sportEntity, SportResponseDto.class);
    }
}
