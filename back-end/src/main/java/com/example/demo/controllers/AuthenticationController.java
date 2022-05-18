package com.example.demo.controllers;

import com.example.demo.dto.requests.authentication.RegisterRequestDto;
import com.example.demo.dto.requests.product.LoginRequestDto;
import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.responses.authentication.LoginResponseDto;
import com.example.demo.dto.responses.user.UserResponseDto;
import com.example.demo.entities.UserEntity;
import com.example.demo.entities.factories.responsebodydto.ResponseBodyDtoFactory;
import com.example.demo.security.providers.JWTProvider;
import com.example.demo.services.interfaces.authentication.AuthenticationService;
import com.example.demo.services.interfaces.permission.PermissionService;
import com.example.demo.services.interfaces.role.RoleService;
import com.example.demo.services.interfaces.user.UserRoleService;
import com.example.demo.services.interfaces.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private ResponseBodyDtoFactory responseBodyDtoFactory;

    @PostMapping("/login")
    public ResponseEntity<ResponseBodyDto<LoginResponseDto>> login(@Valid @RequestBody LoginRequestDto request) {
        String username = request.getUsername();
        String password = request.getPassword();
        LoginResponseDto loginResponseDTO = authService.authenticateUser(username, password);
        ResponseBodyDto<LoginResponseDto> responseBodyDTO = responseBodyDtoFactory.buildResponseBody(loginResponseDTO, "200");

        return ResponseEntity.ok(responseBodyDTO);
    }

    @PostMapping("/login_admin")
    public ResponseEntity<ResponseBodyDto<LoginResponseDto>> loginAdmin(@Valid @RequestBody LoginRequestDto responseDto) {
        String username = responseDto.getUsername();
        String password = responseDto.getPassword();
        LoginResponseDto loginResponseDTO = authService.authenticateAdmin(username, password);
        ResponseBodyDto<LoginResponseDto> responseBodyDTO = responseBodyDtoFactory.buildResponseBody(loginResponseDTO, "200");

        return ResponseEntity.ok(responseBodyDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseBodyDto<UserResponseDto>> register(@Valid @RequestBody RegisterRequestDto request) {
        UserEntity newUser = modelMapper.map(request, UserEntity.class);
        UserResponseDto userResponseDTO = userService.createNormalUser(newUser);
        ResponseBodyDto<UserResponseDto> response = responseBodyDtoFactory.buildResponseBody(userResponseDTO, "200");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/refresh_tokens")
    public ResponseEntity<ResponseBodyDto<LoginResponseDto>> refreshTokens(@RequestHeader(value = "Refresh-Token") String refreshToken) {
        LoginResponseDto responseDTO = authService.refreshAccessToken(refreshToken);
        ResponseBodyDto<LoginResponseDto> responseBodyDto = responseBodyDtoFactory.buildResponseBody(responseDTO, "200");

        return ResponseEntity.ok(responseBodyDto);
    }
}
