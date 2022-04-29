package com.example.demo.services.implementations;

import com.example.demo.dto.responses.GenderResponseDTO;
import com.example.demo.entities.GenderEntity;
import com.example.demo.repositories.GenderRepository;
import com.example.demo.services.interfaces.GenderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenderServiceImpl implements GenderService {
    private ModelMapper modelMapper;

    private GenderRepository genderRepository;

    @Autowired
    public GenderServiceImpl(ModelMapper modelMapper, GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public GenderResponseDTO save(GenderEntity genderEntity) {
        genderEntity = genderRepository.save(genderEntity);

        return modelMapper.map(genderEntity, GenderResponseDTO.class);
    }

    @Override
    public GenderEntity findById(Long id) {
        Optional<GenderEntity> genderEntity = genderRepository.findById(id);

        return genderEntity.orElse(null);
    }
}
