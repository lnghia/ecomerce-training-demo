package com.example.demo.services.implementations;

import com.example.demo.entities.GenderEntity;
import com.example.demo.exceptions.GenderNotFoundException;
import com.example.demo.repositories.GenderRepository;
import com.example.demo.services.interfaces.GenderCrudService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class GenderCrudServiceImpl implements GenderCrudService {
    private ModelMapper modelMappper;

    private GenderRepository genderRepository;

    @Autowired
    public GenderCrudServiceImpl(ModelMapper modelMapper, GenderRepository genderRepository) {
        this.modelMappper = modelMapper;
        this.genderRepository = genderRepository;
    }

    @Override
    public GenderEntity findById(Long id) {
        Optional<GenderEntity> genderEntity = genderRepository.findById(id);

        if (genderEntity.isPresent()) {
            return genderEntity.get();
        }

        throw new GenderNotFoundException();
    }
}
