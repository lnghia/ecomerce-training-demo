package com.example.demo.services.interfaces;

import com.example.demo.dto.responses.UserResponseDto;

public interface UserRoleService {
    UserResponseDto assignRoleToUser(long userId, long roleId);
}
