package com.example.demo.services.implementations;

import com.example.demo.entities.MidsoleEntity;
import com.example.demo.exceptions.MidsoleNotFoundException;
import com.example.demo.repositories.MidsoleRepository;
import com.example.demo.services.interfaces.MidsoleCrudService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class MidsoleCrudServiceImpl implements MidsoleCrudService {
    private ModelMapper modelMapper;

    private MidsoleRepository midsoleRepository;

    @Autowired
    public MidsoleCrudServiceImpl(ModelMapper modelMapper, MidsoleRepository midsoleRepository) {
        this.modelMapper = modelMapper;
        this.midsoleRepository = midsoleRepository;
    }

    @Override
    public MidsoleEntity findById(Long id) {
        Optional<MidsoleEntity> midsoleEntity = midsoleRepository.findById(id);

        if (midsoleEntity.isPresent()) {
            return midsoleEntity.get();
        }

        throw new MidsoleNotFoundException();
    }
}
