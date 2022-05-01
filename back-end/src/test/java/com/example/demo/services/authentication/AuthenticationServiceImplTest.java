package com.example.demo.services.authentication;


import com.example.demo.dto.responses.authentication.LoginResponseDto;
import com.example.demo.entities.UserEntity;
import com.example.demo.exceptions.InvalidTokenException;
import com.example.demo.exceptions.UsernamePasswordInvalidException;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.providers.JWTProvider;
import com.example.demo.services.implementations.authentication.AuthenticationServiceImpl;
import com.example.demo.services.interfaces.authentication.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AuthenticationServiceImplTest {
    UserEntity initialUser;
    AuthenticationService authService;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    JWTProvider jwtProvider;
    ModelMapper modelMapper;

    @BeforeEach
    public void beforeEach() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        jwtProvider = mock(JWTProvider.class);
        modelMapper = mock(ModelMapper.class);
        authService = new AuthenticationServiceImpl(userRepository, passwordEncoder, jwtProvider, modelMapper);
        initialUser = mock(UserEntity.class);
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.ofNullable(null));
        when(userRepository.findByUsername("username")).thenReturn(Optional.of(initialUser));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);
        when(passwordEncoder.matches("password", initialUser.getPassword())).thenReturn(true);
        when(jwtProvider.generateAccessToken(initialUser)).thenReturn("accessToken");
        when(jwtProvider.generateRefreshToken(initialUser)).thenReturn("refreshToken");

        when(jwtProvider.validateRefreshToken(anyString())).thenReturn(false);
        when(jwtProvider.validateRefreshToken("validRefreshToken")).thenReturn(true);
        when(jwtProvider.getUserIdFromJWT(anyString())).thenReturn(2);
        when(jwtProvider.getUserIdFromJWT("validRefreshToken")).thenReturn(1);
        when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
        when(userRepository.findById(1L)).thenReturn(Optional.of(initialUser));
    }

    @Test
    void authenticateUser_ShouldReturnLoginResponseDTO_WhenCredentialsValid() {
        LoginResponseDto result = authService.authenticateUser("username", "password");

        verify(jwtProvider).generateAccessToken(initialUser);
        verify(jwtProvider).generateRefreshToken(initialUser);
        assertThat(result.getRefreshToken(), is("refreshToken"));
        assertThat(result.getAccessToken(), is("accessToken"));
    }

    @Test
    void authenticateUser_ShouldThrowUsernamePasswordInvalidException_WhenPasswordInvalid() {
        assertThrows(UsernamePasswordInvalidException.class, () -> {
            authService.authenticateUser("username", "invalidpassword");
        });
    }

    @Test
    void authenticateUser_ShouldThrowUsernamePasswordInvalidException_WhenUsernameInvalid() {
        assertThrows(UsernamePasswordInvalidException.class, () -> {
            authService.authenticateUser("invalidusername", "password");
        });
    }

    @Test
    void authenticateUser_ShouldThrowUsernamePasswordInvalidException_WhenCredentialsInvalid() {
        assertThrows(UsernamePasswordInvalidException.class, () -> {
            authService.authenticateUser("invalidusername", "invalidpassword");
        });
    }

    @Test
    void refreshAccessToken_ShouldReturnLoginResponseDTO_WhenRefreshTokenValid() {
        LoginResponseDto result = authService.refreshAccessToken("validRefreshToken");

        verify(jwtProvider).generateAccessToken(initialUser);
        verify(jwtProvider).generateRefreshToken(initialUser);
        assertThat(result.getAccessToken(), is("accessToken"));
        assertThat(result.getRefreshToken(), is("refreshToken"));
    }

    @Test
    void refreshAccessToken_ShouldThrowInvalidTokenException_WhenRefreshTokenInvalid() {
        assertThrows(InvalidTokenException.class, () -> {
            authService.refreshAccessToken("invalidRefreshToken");
        });
    }

    @Test
    void shouldCreateAuthenticationServiceImpl() {
        AuthenticationServiceImpl authenticationService = new AuthenticationServiceImpl();

        assertNull(authenticationService.getJwtProvider());
        assertNull(authenticationService.getModelMapper());
        assertNull(authenticationService.getPasswordEncoder());
        assertNull(authenticationService.getUserRepository());
    }
}
