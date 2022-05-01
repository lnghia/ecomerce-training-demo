package com.example.demo.services.interfaces.role;

import com.example.demo.entities.RoleEntity;

public interface RoleService {
    RoleEntity save(RoleEntity roleEntity);

    RoleEntity findByName(String name);

    boolean hasRoleExisted(long id);
}
