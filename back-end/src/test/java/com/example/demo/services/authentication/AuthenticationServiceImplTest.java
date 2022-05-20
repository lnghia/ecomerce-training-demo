package com.example.demo.services.authentication;


import com.example.demo.dto.responses.authentication.LoginResponseDto;
import com.example.demo.entities.RoleEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.exceptions.InvalidTokenException;
import com.example.demo.exceptions.UserBlockedException;
import com.example.demo.exceptions.UsernamePasswordInvalidException;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.providers.JWTProvider;
import com.example.demo.services.implementations.authentication.AuthenticationServiceImpl;
import com.example.demo.services.implementations.role.RoleServiceImpl;
import com.example.demo.services.interfaces.authentication.AuthenticationService;
import com.example.demo.services.interfaces.role.RoleService;
import com.example.demo.utilities.wrapper.RoleUtilityWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AuthenticationServiceImplTest {
    UserEntity initialUser;
    UserEntity normalUser;
    UserEntity adminUser;
    AuthenticationService authService;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    JWTProvider jwtProvider;
    ModelMapper modelMapper;
    RoleService roleService;
    RoleUtilityWrapper roleUtilityWrapper;
    RoleEntity adminRole;
    RoleEntity userRole;

    @BeforeEach
    public void beforeEach() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        jwtProvider = mock(JWTProvider.class);
        modelMapper = mock(ModelMapper.class);
        roleService = mock(RoleServiceImpl.class);
        roleUtilityWrapper = mock(RoleUtilityWrapper.class);
        authService = new AuthenticationServiceImpl(userRepository, passwordEncoder, jwtProvider, modelMapper, roleService, roleUtilityWrapper);
        initialUser = mock(UserEntity.class);
        normalUser = mock(UserEntity.class);
        adminUser = mock(UserEntity.class);
        adminRole = mock(RoleEntity.class);
        userRole = mock(RoleEntity.class);
        Set<RoleEntity> adminRoles = new HashSet<>() {{
            add(adminRole);
        }};
        Set<RoleEntity> userRoles = new HashSet<>() {{
            add(userRole);
        }};

        when(userRepository.findByUsername(anyString())).thenReturn(Optional.ofNullable(null));
        when(userRepository.findByUsername("username")).thenReturn(Optional.of(initialUser));
        when(userRepository.findByUsername("admin")).thenReturn(Optional.of(adminUser));
        when(userRepository.findByUsername("user")).thenReturn(Optional.of(normalUser));

        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);
        when(passwordEncoder.matches("password", initialUser.getPassword())).thenReturn(true);
        when(passwordEncoder.matches("admin_password", adminUser.getPassword())).thenReturn(true);
        when(passwordEncoder.matches("user_password", normalUser.getPassword())).thenReturn(true);

        when(roleUtilityWrapper.getAdminRoleString()).thenReturn("ROLE_ADMIN");
        when(roleService.findByName("ROLE_ADMIN")).thenReturn(adminRole);
        when(adminUser.getRoles()).thenReturn(adminRoles);
        when(normalUser.getRoles()).thenReturn(userRoles);

        when(jwtProvider.generateAccessToken(initialUser)).thenReturn("accessToken");
        when(jwtProvider.generateRefreshToken(initialUser)).thenReturn("refreshToken");
        when(jwtProvider.generateAccessToken(adminUser)).thenReturn("adminAccessToken");
        when(jwtProvider.generateRefreshToken(adminUser)).thenReturn("adminRefreshToken");
        when(jwtProvider.validateRefreshToken(anyString())).thenReturn(false);
        when(jwtProvider.validateRefreshToken("validRefreshToken")).thenReturn(true);
        when(jwtProvider.getUserIdFromJWT(anyString())).thenReturn(2);
        when(jwtProvider.getUserIdFromJWT("validRefreshToken")).thenReturn(1);

        when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
        when(userRepository.findById(1L)).thenReturn(Optional.of(initialUser));
    }

    @Test
    void authenticateUser_ShouldReturnLoginResponseDTO_WhenCredentialsValid() {
        when(initialUser.getIsActive()).thenReturn(true);

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
    void authenticateUser_ShouldThrowUserBlockedException_WhenCredentialsInvalid() {
        when(initialUser.getIsActive()).thenReturn(false);

        assertThrows(UserBlockedException.class, () -> {
            authService.authenticateUser("username", "password");
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
    void authenticateAdmin_ShouldReturnLoginResponseDTO_WhenCredentialsValid() {
        when(adminUser.getIsActive()).thenReturn(true);

        LoginResponseDto result = authService.authenticateAdmin("admin", "admin_password");

        verify(jwtProvider).generateAccessToken(adminUser);
        verify(jwtProvider).generateRefreshToken(adminUser);
        assertThat(result.getRefreshToken(), is("adminRefreshToken"));
        assertThat(result.getAccessToken(), is("adminAccessToken"));
    }

    @Test
    void authenticateAdmin_ShouldThrowUsernamePasswordInvalidException_WhenPasswordInvalid() {
        assertThrows(UsernamePasswordInvalidException.class, () -> {
            authService.authenticateAdmin("username", "invalidpassword");
        });
    }

    @Test
    void authenticateAdmin_ShouldThrowUsernamePasswordInvalidException_WhenUsernameInvalid() {
        assertThrows(UsernamePasswordInvalidException.class, () -> {
            authService.authenticateAdmin("invalidusername", "password");
        });
    }

    @Test
    void authenticateAdmin_ShouldThrowUsernamePasswordInvalidException_WhenCredentialsInvalid() {
        assertThrows(UsernamePasswordInvalidException.class, () -> {
            authService.authenticateAdmin("invalidusername", "invalidpassword");
        });
    }

    @Test
    void authenticateAdmin_ShouldThrowUserBlockedException_WhenCredentialsInvalid() {
        when(adminUser.getIsActive()).thenReturn(false);

        assertThrows(UserBlockedException.class, () -> {
            authService.authenticateAdmin("admin", "admin_password");
        });
    }

    @Test
    void authenticateAdmin_ShouldThrowUsernamePasswordInvalidException_WhenUserHasNoAuthority() {
        when(normalUser.getIsActive()).thenReturn(true);

        assertThrows(UsernamePasswordInvalidException.class, () -> {
            authService.authenticateAdmin("user", "user_password");
        });
    }
}
