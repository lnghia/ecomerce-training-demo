package com.example.demo.services.interfaces.user;

import com.example.demo.dto.responses.user.UserResponseDto;

public interface UserRoleService {
  UserResponseDto assignRoleToUser(long userId, long roleId);
}
