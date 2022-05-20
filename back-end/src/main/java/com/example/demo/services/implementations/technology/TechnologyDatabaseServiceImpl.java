package com.example.demo.services.implementations.technology;

import com.example.demo.configurations.modelmapper.converters.CommonConverter;
import com.example.demo.dto.responses.technology.TechnologyResponseDto;
import com.example.demo.entities.TechnologyEntity;
import com.example.demo.exceptions.TechnologyNotFoundException;
import com.example.demo.repositories.TechnologyRepository;
import com.example.demo.services.interfaces.technology.TechnologyDatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TechnologyDatabaseServiceImpl implements TechnologyDatabaseService {
  private final TechnologyRepository technologyRepository;

  private final CommonConverter converter;

  @Override
  public List<TechnologyEntity> findByIds(List<Long> ids) {
    List<TechnologyEntity> technologyEntities = technologyRepository.findAllById(ids);

    if (technologyEntities.size() != ids.size()) {
      throw new TechnologyNotFoundException();
    }

    return technologyEntities;
  }

  @Override
  public List<TechnologyResponseDto> findAll() {
    List<TechnologyEntity> technologyEntityList = technologyRepository.findAll();
    List<TechnologyResponseDto> result;

    result =
        technologyEntityList.stream()
            .map(
                technologyEntity ->
                    converter.convertToResponse(technologyEntity, TechnologyResponseDto.class))
            .collect(Collectors.toList());

    return result;
  }

  @Override
  public TechnologyEntity findById(Long id) {
    return technologyRepository.findById(id).orElseThrow(TechnologyNotFoundException::new);
  }
}
