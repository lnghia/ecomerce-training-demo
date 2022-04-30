package com.example.demo.services.implementations;

import com.example.demo.entities.SizeEntity;
import com.example.demo.exceptions.SizeNotFoundException;
import com.example.demo.repositories.SizeRepository;
import com.example.demo.services.interfaces.SizeService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@Service
@NoArgsConstructor
public class SizeServiceImpl implements SizeService {
    private ModelMapper modelMapper;

    private SizeRepository sizeRepository;

    @Autowired
    public SizeServiceImpl(ModelMapper modelMapper, SizeRepository sizeRepository) {
        this.modelMapper = modelMapper;
        this.sizeRepository = sizeRepository;
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
    public ArrayList<SizeEntity> findByIds(Set<Long> ids) {
        ArrayList<SizeEntity> sizeEntities = (ArrayList<SizeEntity>) sizeRepository.findAllById(ids);

        return sizeEntities;
    }
}
