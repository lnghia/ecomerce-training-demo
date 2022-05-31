package com.example.demo.dto.requests.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
public class EditUserRequestDto {
  private String firstName;

  private String lastName;

  private String avatar;

  @Email private String email;
}
