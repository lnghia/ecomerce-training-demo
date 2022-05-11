package com.example.demo.services.interfaces.size;

import com.example.demo.dto.responses.size.SizeResponseDto;
import com.example.demo.entities.SizeEntity;

import java.util.List;
import java.util.Set;

public interface SizeService {
    SizeEntity findById(Long id);

    List<SizeEntity> findByIds(Set<Long> ids);

    List<SizeResponseDto> getAll();
}
