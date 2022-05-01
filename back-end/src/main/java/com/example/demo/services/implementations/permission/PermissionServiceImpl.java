package com.example.demo.services.implementations.permission;

import com.example.demo.entities.PermissionEntity;
import com.example.demo.repositories.PermissionRepository;
import com.example.demo.services.interfaces.permission.PermissionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {
    private PermissionRepository permissionRepository;

    private ModelMapper modelMapper;

    @Autowired
    public PermissionServiceImpl(PermissionRepository permissionRepository, ModelMapper modelMapper) {
        this.permissionRepository = permissionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PermissionEntity save(PermissionEntity permissionEntity) {
        return permissionRepository.save(permissionEntity);
    }
}
