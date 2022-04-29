package com.example.demo.services.implementations;

import com.example.demo.entities.UpperEntity;
import com.example.demo.exceptions.UpperNotFoundException;
import com.example.demo.repositories.UpperRepository;
import com.example.demo.services.interfaces.UpperCrudService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class UpperCrudServiceImpl implements UpperCrudService {
    private ModelMapper modelMapper;

    private UpperRepository upperRepository;

    @Autowired
    public UpperCrudServiceImpl(ModelMapper modelMapper, UpperRepository upperRepository) {
        this.modelMapper = modelMapper;
        this.upperRepository = upperRepository;
    }

    @Override
    public UpperEntity findById(Long id) {
        Optional<UpperEntity> upperEntity = upperRepository.findById(id);

        if (upperEntity.isPresent()) {
            return upperEntity.get();
        }

        throw new UpperNotFoundException();
    }
}
