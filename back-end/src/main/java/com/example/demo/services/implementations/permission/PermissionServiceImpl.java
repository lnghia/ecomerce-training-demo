package com.example.demo.services.implementations.permission;

import com.example.demo.entities.PermissionEntity;
import com.example.demo.repositories.PermissionRepository;
import com.example.demo.services.interfaces.permission.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
  private final PermissionRepository permissionRepository;

  @Override
  public PermissionEntity save(PermissionEntity permissionEntity) {
    return permissionRepository.save(permissionEntity);
  }
}
