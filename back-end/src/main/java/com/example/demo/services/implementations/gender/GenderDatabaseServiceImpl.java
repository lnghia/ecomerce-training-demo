package com.example.demo.services.implementations.gender;

import com.example.demo.configurations.modelmapper.converters.CommonConverter;
import com.example.demo.dto.responses.gender.GenderResponseDto;
import com.example.demo.entities.GenderEntity;
import com.example.demo.exceptions.GenderNotFoundException;
import com.example.demo.repositories.GenderRepository;
import com.example.demo.services.interfaces.gender.GenderDatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenderDatabaseServiceImpl implements GenderDatabaseService {
  private final CommonConverter converter;

  private final GenderRepository genderRepository;

  @Override
  public GenderEntity findById(Long id) {
    Optional<GenderEntity> genderEntity = genderRepository.findById(id);

    if (genderEntity.isPresent()) {
      return genderEntity.get();
    }

    throw new GenderNotFoundException();
  }

  @Override
  public List<GenderResponseDto> findAll() {
    List<GenderEntity> genders = genderRepository.findAll();
    List<GenderResponseDto> result;

    result =
        genders.stream()
            .map(genderEntity -> converter.convertToResponse(genderEntity, GenderResponseDto.class))
            .collect(Collectors.toList());

    return result;
  }
}
