package com.example.demo.controllers.admin;

import com.example.demo.dto.requests.product.AssignRoleToUserRequestDto;
import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.responses.user.UserListResponseDto;
import com.example.demo.dto.responses.user.UserResponseDto;
import com.example.demo.entities.factories.responsebodydto.ResponseBodyDtoFactory;
import com.example.demo.services.interfaces.user.UserRoleService;
import com.example.demo.services.interfaces.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/admin/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final UserRoleService userRoleService;

    private final ResponseBodyDtoFactory responseBodyDtoFactory;

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/active")
    public ResponseEntity<ResponseBodyDto> activeUser(@RequestParam(name = "id") Long userId) {
        UserResponseDto userResponseDto = userService.activeUser(userId);
        ResponseBodyDto<UserResponseDto> responseBody = responseBodyDtoFactory.buildResponseBody(userResponseDto, "200");

        return ResponseEntity.ok(responseBody);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/de_active")
    public ResponseEntity<ResponseBodyDto> deactiveUser(@RequestParam(name = "id") Long userId) {
        UserResponseDto userResponseDto = userService.deActiveUser(userId);
        ResponseBodyDto<UserResponseDto> responseBody = responseBodyDtoFactory.buildResponseBody(userResponseDto, "200");

        return ResponseEntity.ok(responseBody);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/active")
    public ResponseEntity<ResponseBodyDto> getListNormalUser() {
        List<UserListResponseDto> users = userService.getListNormalUser();
        ResponseBodyDto<List<UserListResponseDto>> responseBody = responseBodyDtoFactory.buildResponseBody(users, "200");

        return ResponseEntity.ok(responseBody);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/de_active")
    public ResponseEntity<ResponseBodyDto> getListBlockedUser() {
        List<UserListResponseDto> users = userService.getListBlockedUser();
        ResponseBodyDto<List<UserListResponseDto>> responseBody = responseBodyDtoFactory.buildResponseBody(users, "200");

        return ResponseEntity.ok(responseBody);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/assign_role")
    public ResponseEntity<ResponseBodyDto> assignRoleToUser(@Valid @RequestBody AssignRoleToUserRequestDto requestBody) {
        UserResponseDto userResponseDTO = userRoleService.assignRoleToUser(requestBody.getUserId(), requestBody.getRoleId());
        ResponseBodyDto<UserResponseDto> responseBodyDto = responseBodyDtoFactory.buildResponseBody(userResponseDTO, "200");

        return ResponseEntity.ok(responseBodyDto);
    }
}