package com.example.demo.services.interfaces;

import com.example.demo.dto.responses.LoginResponseDTO;

public interface AuthenticationService {
    LoginResponseDTO authenticateUser(String username, String password);

    LoginResponseDTO refreshAccessToken(String refreshToken);
}
