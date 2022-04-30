package com.example.demo.services.interfaces;

import com.example.demo.entities.SizeEntity;

import java.util.ArrayList;
import java.util.Set;

public interface SizeService {
    SizeEntity findById(Long id);

    ArrayList<SizeEntity> findByIds(Set<Long> ids);
}
