package com.example.demo.services.implementations;

import com.example.demo.entities.SportEntity;
import com.example.demo.repositories.SportRepository;
import com.example.demo.services.interfaces.SportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

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
}
