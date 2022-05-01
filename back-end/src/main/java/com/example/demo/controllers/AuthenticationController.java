package com.example.demo.controllers;

import com.example.demo.dto.requests.authentication.RegisterRequestDto;
import com.example.demo.dto.requests.product.AssignRoleToUserRequestDto;
import com.example.demo.dto.requests.product.LoginRequestDto;
import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.responses.authentication.LoginResponseDto;
import com.example.demo.dto.responses.user.UserResponseDto;
import com.example.demo.entities.CustomUserDetails;
import com.example.demo.entities.RoleEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.security.providers.JWTProvider;
import com.example.demo.services.interfaces.authentication.AuthenticationService;
import com.example.demo.services.interfaces.permission.PermissionService;
import com.example.demo.services.interfaces.role.RoleService;
import com.example.demo.services.interfaces.user.UserRoleService;
import com.example.demo.services.interfaces.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/test")
    public String test() {
        UserEntity user = ((CustomUserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getUser();


        return "test.";
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseBodyDto> login(@Valid @RequestBody LoginRequestDto request) {
        String username = request.getUsername();
        String password = request.getPassword();
        LoginResponseDto loginResponseDTO = authService.authenticateUser(username, password);
        ResponseBodyDto responseBodyDTO = ResponseBodyDto.builder().data(loginResponseDTO).build();

        return ResponseEntity.ok(responseBodyDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseBodyDto> register(@Valid @RequestBody RegisterRequestDto request) {
        UserEntity newUser = modelMapper.map(request, UserEntity.class);
        UserResponseDto userResponseDTO = userService.createNormalUser(newUser);
        ResponseBodyDto response = ResponseBodyDto.builder().data(userResponseDTO).build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/refresh_tokens")
    public ResponseEntity<ResponseBodyDto> refreshTokens(@RequestHeader(value = "Refresh-Token", required = true) String refreshToken) {
        LoginResponseDto responseDTO = authService.refreshAccessToken(refreshToken);

        return ResponseEntity.ok(ResponseBodyDto.builder().data(responseDTO).build());
    }

    @GetMapping("/ttt")
    public ResponseEntity<ResponseBodyDto> ttt() {
        ArrayList<RoleEntity> tmp = new ArrayList<>(userService.getUserGrantedPermissions(1L));

        return ResponseEntity.ok(ResponseBodyDto.builder().data(tmp).build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/assign_role")
    public ResponseEntity<ResponseBodyDto> assignRoleToUser(@Valid @RequestBody AssignRoleToUserRequestDto requestBody) {
        UserResponseDto userResponseDTO = userRoleService.assignRoleToUser(requestBody.getUserId(), requestBody.getRoleId());

        return ResponseEntity.ok(ResponseBodyDto.builder().data(userResponseDTO).build());
    }

//    @GetMapping("/iii")
//    public ResponseEntity<ResponseBodyDTO> iii() {
//        PermissionEntity permissionEntity = PermissionEntity.builder().value(2).name("write").build();
//        PermissionEntity newPermission = permissionService.save(permissionEntity);
//        List<PermissionEntity> tmp = new ArrayList<>(Arrays.asList(newPermission));
//        RoleEntity roleEntity = RoleEntity.builder().name("user").value(2).permissions(tmp).build();
//        RoleEntity newRoleEntity = roleService.save(roleEntity);
//
//        return ResponseEntity.ok(new ResponseBodyDTO());
//    }

//    @GetMapping("/ooo")
//    public ResponseEntity<ResponseBodyDTO> ooo() {
//        RoleEntity roleEntity = roleService.findByName("user");
//        UserEntity user = userService.getUserById(1L);
//        user.getRoles().add(roleEntity);
//        user = userService.save(user);
//
//        return ResponseEntity.ok(ResponseBodyDTO.builder().data(user.getRoles()).build());
//    }
}
