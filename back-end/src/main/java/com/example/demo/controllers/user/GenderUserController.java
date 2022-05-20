package com.example.demo.controllers.user;

import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.responses.gender.GenderResponseDto;
import com.example.demo.entities.factories.responsebodydto.ResponseBodyDtoFactory;
import com.example.demo.services.interfaces.gender.GenderDatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/gender")
@RequiredArgsConstructor
public class GenderUserController {
  private final GenderDatabaseService genderDatabaseService;

  private final ResponseBodyDtoFactory responseBodyDtoFactory;

  @GetMapping
  public ResponseEntity<ResponseBodyDto<List<GenderResponseDto>>> getAll() {
    List<GenderResponseDto> genderResponseDtoList = genderDatabaseService.findAll();
    ResponseBodyDto<List<GenderResponseDto>> responseData =
        responseBodyDtoFactory.buildResponseBody(genderResponseDtoList, "200");

    return ResponseEntity.ok(responseData);
  }
}
