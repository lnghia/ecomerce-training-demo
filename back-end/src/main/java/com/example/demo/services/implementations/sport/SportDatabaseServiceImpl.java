package com.example.demo.services.implementations.sport;

import com.example.demo.dto.responses.sport.SportResponseDto;
import com.example.demo.entities.SportEntity;
import com.example.demo.exceptions.SportNotFoundException;
import com.example.demo.repositories.SportRepository;
import com.example.demo.services.interfaces.sport.SportDatabaseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SportDatabaseServiceImpl implements SportDatabaseService {
    private ModelMapper modelMapper;

    private SportRepository sportRepository;

    @Autowired
    public SportDatabaseServiceImpl(ModelMapper modelMapper, SportRepository sportRepository) {
        this.modelMapper = modelMapper;
        this.sportRepository = sportRepository;
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
    public SportEntity findById(Long id) {
        Optional<SportEntity> sportEntity = sportRepository.findById(id);

        if (sportEntity.isPresent()) {
            return sportEntity.get();
        }

        throw new SportNotFoundException();
    }
}
