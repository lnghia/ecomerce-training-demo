package com.example.demo.dto.responses.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEditResponseDto {
  private String firstName;

  private String lastName;

  private String avatar;

  private String email;
}
