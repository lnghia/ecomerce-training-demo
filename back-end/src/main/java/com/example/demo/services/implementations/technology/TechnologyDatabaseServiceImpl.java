package com.example.demo.services.implementations.technology;

import com.example.demo.configurations.modelmapper.converters.CommonConverter;
import com.example.demo.dto.responses.technology.TechnologyResponseDto;
import com.example.demo.entities.TechnologyEntity;
import com.example.demo.exceptions.TechnologyNotFoundException;
import com.example.demo.repositories.TechnologyRepository;
import com.example.demo.services.interfaces.technology.TechnologyDatabaseService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class TechnologyDatabaseServiceImpl implements TechnologyDatabaseService {
    private TechnologyRepository technologyRepository;

    private CommonConverter converter;

    public TechnologyDatabaseServiceImpl(TechnologyRepository technologyRepository,
                                         CommonConverter converter) {
        this.technologyRepository = technologyRepository;
        this.converter = converter;
    }

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

        result = technologyEntityList.stream().map(technologyEntity -> converter.convertToResponse(technologyEntity, TechnologyResponseDto.class)).collect(Collectors.toList());

        return result;
    }

    @Override
    public TechnologyEntity findById(Long id) {
        Optional<TechnologyEntity> technologyEntity = technologyRepository.findById(id);

        if (technologyEntity.isPresent()) {
            return technologyEntity.get();
        }

        throw new TechnologyNotFoundException();
    }
}
