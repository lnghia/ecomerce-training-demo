package com.example.demo.dto.responses.authentication;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class LoginResponseDto {
    private String accessToken;

    private String refreshToken;
}
