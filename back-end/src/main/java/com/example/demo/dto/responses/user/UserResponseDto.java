package com.example.demo.dto.responses.user;

import com.example.demo.dto.responses.role.RoleResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserResponseDto {
  protected long id;

  protected String firstName;

  protected String lastName;

  protected String email;

  protected Set<RoleResponseDto> roles;

  protected String createdDate;

  protected String lastModifiedDate;

  protected boolean isActive;
}
