package com.example.demo.dto.responses.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserListResponseDto extends UserResponseDto {
  private String username;

  private String avatar;
}
