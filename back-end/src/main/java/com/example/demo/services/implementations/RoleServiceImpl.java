package com.example.demo.services.implementations;

import com.example.demo.entities.RoleEntity;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.services.interfaces.RoleService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    private ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RoleEntity save(RoleEntity roleEntity) {
        return roleRepository.save(roleEntity);
    }

    @Override
    public RoleEntity findByName(String name) {
//        String nameInUppercase = name.toUpperCase();
        Optional<RoleEntity> roleEntity = roleRepository.findByName(name);

        return roleEntity.orElse(null);
    }

    @Override
    public boolean hasRoleExisted(long id) {
        Optional<RoleEntity> roleEntity = roleRepository.findById(id);

        return roleEntity.isPresent();
    }
}
