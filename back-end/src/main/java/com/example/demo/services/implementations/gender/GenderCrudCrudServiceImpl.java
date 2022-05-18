package com.example.demo.services.implementations.gender;

import com.example.demo.configurations.modelmapper.converters.CommonConverter;
import com.example.demo.dto.requests.gender.CreateGenderRequestDto;
import com.example.demo.dto.requests.gender.DeleteGenderRequestDto;
import com.example.demo.dto.requests.gender.UpdateGenderRequestDto;
import com.example.demo.dto.responses.gender.GenderResponseDto;
import com.example.demo.entities.GenderEntity;
import com.example.demo.repositories.GenderRepository;
import com.example.demo.services.interfaces.gender.GenderCrudService;
import com.example.demo.services.interfaces.gender.GenderDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenderCrudCrudServiceImpl implements GenderCrudService {
    private final CommonConverter converter;

    private final GenderDatabaseService genderDatabaseService;

    private final GenderRepository genderRepository;

    @Autowired
    public GenderCrudCrudServiceImpl(CommonConverter converter,
                                     GenderDatabaseService genderDatabaseService,
                                     GenderRepository genderRepository) {
        this.genderDatabaseService = genderDatabaseService;
        this.converter = converter;
        this.genderRepository = genderRepository;
    }

    @Override
    public GenderResponseDto createGender(CreateGenderRequestDto requestDto) {
        GenderEntity genderEntity = new GenderEntity();

        converter.convertToEntity(requestDto, genderEntity);
        return save(genderEntity);
    }

    @Override
    public GenderResponseDto updateGender(UpdateGenderRequestDto requestDto) {
        GenderEntity genderEntity = genderDatabaseService.findById(requestDto.getGenderId());

        converter.convertToEntity(requestDto, genderEntity);
        return save(genderEntity);
    }

    @Override
    public GenderResponseDto deleteGender(DeleteGenderRequestDto requestDto) {
        return null;
    }

    GenderResponseDto save(GenderEntity genderEntity) {
        genderEntity = genderRepository.save(genderEntity);

        return converter.convertToResponse(genderEntity, GenderResponseDto.class);
    }
}
