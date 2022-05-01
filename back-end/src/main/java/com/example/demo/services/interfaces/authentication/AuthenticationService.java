package com.example.demo.services.interfaces.authentication;

import com.example.demo.dto.responses.authentication.LoginResponseDto;

public interface AuthenticationService {
    LoginResponseDto authenticateUser(String username, String password);

    LoginResponseDto refreshAccessToken(String refreshToken);
}
