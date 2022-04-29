package com.example.demo.services.interfaces;

import com.example.demo.dto.responses.LoginResponseDto;

public interface AuthenticationService {
    LoginResponseDto authenticateUser(String username, String password);

    LoginResponseDto refreshAccessToken(String refreshToken);
}
