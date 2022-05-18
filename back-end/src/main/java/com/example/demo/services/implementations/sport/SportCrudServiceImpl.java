package com.example.demo.services.implementations.sport;

import com.example.demo.dto.requests.sport.CreateSportRequestDto;
import com.example.demo.dto.requests.sport.UpdateSportRequestDto;
import com.example.demo.dto.responses.sport.SportResponseDto;
import com.example.demo.entities.SportEntity;
import com.example.demo.exceptions.SportNotFoundException;
import com.example.demo.repositories.SportRepository;
import com.example.demo.services.interfaces.sport.SportCrudService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class SportCrudServiceImpl implements SportCrudService {
    private ModelMapper modelMapper;

    private SportRepository sportRepository;

    @Autowired
    public SportCrudServiceImpl(ModelMapper modelMapper, SportRepository sportRepository) {
        this.modelMapper = modelMapper;
        this.sportRepository = sportRepository;
    }

    @Override
    public SportResponseDto updateSport(Long sportId, UpdateSportRequestDto requestDto) {
        SportEntity sportEntity = sportRepository.findById(sportId).orElseThrow(SportNotFoundException::new);

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
