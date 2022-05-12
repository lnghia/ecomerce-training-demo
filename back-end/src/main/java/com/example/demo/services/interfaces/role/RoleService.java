package com.example.demo.services.interfaces.role;

import com.example.demo.dto.responses.role.RoleResponseDto;
import com.example.demo.entities.RoleEntity;

import java.util.List;

public interface RoleService {
    RoleEntity save(RoleEntity roleEntity);

    RoleEntity findByName(String name);

    boolean hasRoleExisted(long id);

    List<RoleResponseDto> findAll();
}
