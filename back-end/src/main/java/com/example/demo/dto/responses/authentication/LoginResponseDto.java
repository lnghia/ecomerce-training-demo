package com.example.demo.dto.responses.authentication;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
public class LoginResponseDto {
  private String accessToken;

  private String refreshToken;
}
