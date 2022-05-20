package com.example.demo.dto.responses.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserListResponseDto extends UserResponseDto {
  private String username;

  private String avatar;
}
