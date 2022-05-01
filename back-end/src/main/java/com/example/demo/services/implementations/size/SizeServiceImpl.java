package com.example.demo.services.implementations.size;

import com.example.demo.entities.SizeEntity;
import com.example.demo.exceptions.SizeNotFoundException;
import com.example.demo.repositories.SizeRepository;
import com.example.demo.services.interfaces.size.SizeService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<SizeEntity> findByIds(Set<Long> ids) {
        List<SizeEntity> sizeEntities = sizeRepository.findAllById(ids);

        if (sizeEntities.size() != ids.size()) {
            throw new SizeNotFoundException();
        }

        return sizeEntities;
    }
}
