package com.example.demo.controllers.user;

import com.example.demo.dto.requests.user.EditUserRequestDto;
import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.responses.user.UserEditResponseDto;
import com.example.demo.entities.UserEntity;
import com.example.demo.entities.factories.responsebodydto.ResponseBodyDtoFactory;
import com.example.demo.services.interfaces.user.UserService;
import com.example.demo.utilities.authentication.AuthenticationUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/users")
@RequiredArgsConstructor
public class UserUserController {
  private final UserService userService;

  private final ResponseBodyDtoFactory responseBodyDtoFactory;

  private final AuthenticationUtility authenticationUtility;

  @PutMapping("/edit_info")
  public ResponseEntity<ResponseBodyDto<UserEditResponseDto>> editUser(
      @Valid @RequestBody EditUserRequestDto requestDto) {
    UserEntity editor = authenticationUtility.getUserDetailFromSecurityContext();
    UserEditResponseDto responseDto = userService.editUser(requestDto, editor);
    ResponseBodyDto<UserEditResponseDto> responseBodyDto =
        responseBodyDtoFactory.buildResponseBody(responseDto, "200");

    return ResponseEntity.ok(responseBodyDto);
  }
}
