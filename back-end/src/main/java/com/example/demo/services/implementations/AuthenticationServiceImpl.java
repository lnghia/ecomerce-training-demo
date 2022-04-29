package com.example.demo.services.implementations;

import com.example.demo.dto.responses.LoginResponseDto;
import com.example.demo.entities.UserEntity;
import com.example.demo.exceptions.InvalidTokenException;
import com.example.demo.exceptions.UsernamePasswordInvalidException;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.providers.JWTProvider;
import com.example.demo.services.interfaces.AuthenticationService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
@Getter
public class AuthenticationServiceImpl implements AuthenticationService {
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private JWTProvider jwtProvider;

    private ModelMapper modelMapper;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTProvider jwtProvider, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.modelMapper = modelMapper;
    }

    @Override
    public LoginResponseDto authenticateUser(String username, String password) {
        Optional<UserEntity> user = userRepository.findByUsername(username);

        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            String accessToken = jwtProvider.generateAccessToken(user.get());
            String refreshToken = jwtProvider.generateRefreshToken(user.get());
            LoginResponseDto loginResponseDTO = new LoginResponseDto(accessToken, refreshToken);

            return loginResponseDTO;
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
}
