package com.example.demo.services.implementations.size;

import com.example.demo.dto.requests.size.SizeRequestDto;
import com.example.demo.dto.responses.size.SizeResponseDto;
import com.example.demo.entities.SizeEntity;
import com.example.demo.exceptions.SizeNotFoundException;
import com.example.demo.repositories.SizeRepository;
import com.example.demo.services.interfaces.size.SizeCrudService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class SizeCrudServiceImpl implements SizeCrudService {
    private ModelMapper modelMapper;

    private SizeRepository sizeRepository;

    @Autowired
    public SizeCrudServiceImpl(ModelMapper modelMapper, SizeRepository sizeRepository) {
        this.modelMapper = modelMapper;
        this.sizeRepository = sizeRepository;
    }

    @Override
    public SizeResponseDto createSize(SizeRequestDto requestDto) {
        String name = requestDto.getName();
        String description = requestDto.getDescription();

        SizeEntity sizeEntity = SizeEntity.builder().name(name).description(description).build();
        sizeEntity = sizeRepository.save(sizeEntity);

        return modelMapper.map(sizeEntity, SizeResponseDto.class);
    }

    @Override
    public SizeResponseDto updateSize(Long sizeId, SizeRequestDto requestDto) {
        SizeEntity sizeEntity = sizeRepository.findById(sizeId).orElseThrow(SizeNotFoundException::new);

        modelMapper.map(requestDto, sizeEntity);
        sizeEntity = sizeRepository.save(sizeEntity);

        return modelMapper.map(sizeEntity, SizeResponseDto.class);
    }
}
