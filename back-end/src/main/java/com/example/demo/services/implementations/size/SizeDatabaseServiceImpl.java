package com.example.demo.services.implementations.size;

import com.example.demo.configurations.modelmapper.converters.CommonConverter;
import com.example.demo.dto.responses.size.SizeResponseDto;
import com.example.demo.entities.SizeEntity;
import com.example.demo.exceptions.SizeNotFoundException;
import com.example.demo.repositories.SizeRepository;
import com.example.demo.services.interfaces.size.SizeDatabaseService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class SizeDatabaseServiceImpl implements SizeDatabaseService {
    private SizeRepository sizeRepository;

    private CommonConverter converter;

    public SizeDatabaseServiceImpl(SizeRepository sizeRepository,
                                   CommonConverter converter) {
        this.sizeRepository = sizeRepository;
        this.converter = converter;
    }

    @Override
    public SizeEntity findById(Long id) {
        Optional<SizeEntity> sizeEntity = sizeRepository.findById(id);

        if (sizeEntity.isPresent()) {
            return sizeEntity.get();
        }

        throw new SizeNotFoundException();
    }

    @Override
    public List<SizeEntity> findByIds(Set<Long> ids) {
        List<SizeEntity> sizeEntities = sizeRepository.findAllById(ids);

        if (sizeEntities.size() != ids.size()) {
            throw new SizeNotFoundException();
        }

        return sizeEntities;
    }

    @Override
    public List<SizeResponseDto> getAll() {
        List<SizeEntity> sizeEntities = sizeRepository.findAll();

        return sizeEntities.stream().map(sizeEntity -> converter.convertToResponse(sizeEntity, SizeResponseDto.class)).collect(Collectors.toList());
    }
}
