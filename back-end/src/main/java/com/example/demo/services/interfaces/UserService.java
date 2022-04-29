package com.example.demo.services.interfaces;

import com.example.demo.dto.responses.UserResponseDto;
import com.example.demo.entities.RoleEntity;
import com.example.demo.entities.UserEntity;

import java.util.Collection;

public interface UserService {
    UserEntity getUserById(long id);

    UserEntity getUserByUsername(String username);

    UserResponseDto createNormalUser(UserEntity newUser);

    Collection<RoleEntity> getUserGrantedPermissions(long id);

    UserEntity save(UserEntity user);

    boolean hasUserExisted(long id);

    boolean hasUserExisted(String email);
}
