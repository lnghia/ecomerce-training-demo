package com.example.demo.services.implementations.gender;

import com.example.demo.dto.requests.gender.CreateGenderRequestDto;
import com.example.demo.dto.requests.gender.DeleteGenderRequestDto;
import com.example.demo.dto.requests.gender.UpdateGenderRequestDto;
import com.example.demo.dto.responses.gender.GenderResponseDto;
import com.example.demo.entities.GenderEntity;
import com.example.demo.repositories.GenderRepository;
import com.example.demo.services.interfaces.gender.GenderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public GenderResponseDto save(GenderEntity genderEntity) {
        genderEntity = genderRepository.save(genderEntity);

        return modelMapper.map(genderEntity, GenderResponseDto.class);
    }

    @Override
    public GenderEntity findById(Long id) {
        Optional<GenderEntity> genderEntity = genderRepository.findById(id);

        return genderEntity.orElse(null);
    }

    @Override
    public List<GenderResponseDto> findAll() {
        List<GenderEntity> genders = genderRepository.findAll();
        List<GenderResponseDto> result = null;

        result = genders.stream().map(genderEntity -> {
            return modelMapper.map(genderEntity, GenderResponseDto.class);
        }).collect(Collectors.toList());

        return result;
    }

    @Override
    public GenderResponseDto createGender(CreateGenderRequestDto requestDto) {
        GenderEntity genderEntity = new GenderEntity();

        modelMapper.map(requestDto, genderEntity);
        genderEntity = genderRepository.save(genderEntity);

        return modelMapper.map(genderEntity, GenderResponseDto.class);
    }

    @Override
    public GenderResponseDto updateGender(UpdateGenderRequestDto requestDto) {
        GenderEntity genderEntity = findById(requestDto.getGenderId());

        modelMapper.map(requestDto, genderEntity);
        genderEntity = genderRepository.save(genderEntity);

        return modelMapper.map(genderEntity, GenderResponseDto.class);
    }

    @Override
    public GenderResponseDto deleteGender(DeleteGenderRequestDto requestDto) {
        return null;
    }
}
