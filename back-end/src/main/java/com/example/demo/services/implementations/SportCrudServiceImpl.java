package com.example.demo.services.implementations;

import com.example.demo.entities.SportEntity;
import com.example.demo.exceptions.SportNotFoundException;
import com.example.demo.repositories.SportRepository;
import com.example.demo.services.interfaces.SportCrudService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public SportEntity findById(Long id) {
        Optional<SportEntity> sportEntity = sportRepository.findById(id);

        if (sportEntity.isPresent()) {
            return sportEntity.get();
        }

        throw new SportNotFoundException();
    }
}
