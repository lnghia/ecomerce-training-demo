package com.example.demo.services.implementations.authentication;

import com.example.demo.dto.responses.authentication.LoginResponseDto;
import com.example.demo.entities.RoleEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.exceptions.InvalidTokenException;
import com.example.demo.exceptions.UserBlockedException;
import com.example.demo.exceptions.UsernamePasswordInvalidException;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.providers.JWTProvider;
import com.example.demo.services.interfaces.authentication.AuthenticationService;
import com.example.demo.services.interfaces.role.RoleService;
import com.example.demo.utilities.wrapper.RoleUtilityWrapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JWTProvider jwtProvider;

    private final ModelMapper modelMapper;

    private final RoleService roleService;

    private final RoleUtilityWrapper roleUtilityWrapper;

    @Override
    public LoginResponseDto authenticateUser(String username, String password) {
        Optional<UserEntity> user = userRepository.findByUsername(username);

        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            UserEntity userEntity = user.get();

            if (!userEntity.getIsActive()) {
                throw new UserBlockedException();
            }

            String accessToken = jwtProvider.generateAccessToken(userEntity);
            String refreshToken = jwtProvider.generateRefreshToken(userEntity);

            return new LoginResponseDto(accessToken, refreshToken);
        }

        throw new UsernamePasswordInvalidException();
    }

    @Override
    public LoginResponseDto refreshAccessToken(String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            long userId = jwtProvider.getUserIdFromJWT(refreshToken);
            Optional<UserEntity> user = userRepository.findById(userId);

            if (user.isPresent()) {
                String newAccessToken = jwtProvider.generateAccessToken(user.get());
                String newRefreshToken = jwtProvider.generateRefreshToken(user.get());

                return LoginResponseDto.builder().accessToken(newAccessToken).refreshToken(newRefreshToken).build();
            }
        }

        throw new InvalidTokenException();
    }

    @Override
    public LoginResponseDto authenticateAdmin(String username, String password) {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        RoleEntity adminRole = roleService.findByName(roleUtilityWrapper.getAdminRoleString());

        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword()) && user.get().getRoles().contains(adminRole)) {
            UserEntity userEntity = user.get();

            if (!userEntity.getIsActive()) {
                throw new UserBlockedException();
            }

            String accessToken = jwtProvider.generateAccessToken(userEntity);
            String refreshToken = jwtProvider.generateRefreshToken(userEntity);

            return new LoginResponseDto(accessToken, refreshToken);
        }

        throw new UsernamePasswordInvalidException();
    }
}
