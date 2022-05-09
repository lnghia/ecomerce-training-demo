package com.example.demo.services.interfaces.user;

import com.example.demo.dto.requests.user.UserRateProductRequestDto;
import com.example.demo.dto.responses.user.UserRateProductResponseDto;
import com.example.demo.dto.responses.user.UserResponseDto;
import com.example.demo.entities.RoleEntity;
import com.example.demo.entities.UserEntity;

import java.util.Collection;

public interface UserService {
    UserEntity getUserById(long id);

    UserEntity findById(Long id);

    UserEntity getUserByUsername(String username);

    UserResponseDto createNormalUser(UserEntity newUser);

    Collection<RoleEntity> getUserGrantedPermissions(long id);

    UserEntity save(UserEntity user);

    boolean hasUserExisted(long id);

    boolean hasUserExisted(String email);

    UserRateProductResponseDto rateProduct(UserRateProductRequestDto requestDto, UserEntity userEntity);
}
