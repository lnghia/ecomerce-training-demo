package com.example.demo.services.interfaces;

import com.example.demo.entities.SizeEntity;

import java.util.List;
import java.util.Set;

public interface SizeService {
    SizeEntity findById(Long id);

    List<SizeEntity> findByIds(Set<Long> ids);
}
