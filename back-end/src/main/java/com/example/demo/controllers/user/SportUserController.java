package com.example.demo.controllers.user;

import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.responses.sport.SportResponseDto;
import com.example.demo.entities.factories.responsebodydto.ResponseBodyDtoFactory;
import com.example.demo.services.interfaces.sport.SportDatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/sport")
@RequiredArgsConstructor
public class SportUserController {
  private final SportDatabaseService sportDatabaseService;

  private final ResponseBodyDtoFactory responseBodyDtoFactory;

  @GetMapping
  public ResponseEntity<ResponseBodyDto<List<SportResponseDto>>> getAll() {
    List<SportResponseDto> sportResponseDtoList = sportDatabaseService.findAll();
    ResponseBodyDto<List<SportResponseDto>> responseBody =
        responseBodyDtoFactory.buildResponseBody(sportResponseDtoList, "200");

    return ResponseEntity.ok(responseBody);
  }
}
