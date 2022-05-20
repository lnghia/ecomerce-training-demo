package com.example.demo.controllers.user;

import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.responses.technology.TechnologyResponseDto;
import com.example.demo.entities.factories.responsebodydto.ResponseBodyDtoFactory;
import com.example.demo.services.interfaces.technology.TechnologyDatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/technology")
@RequiredArgsConstructor
public class TechnologyUserController {
  private final TechnologyDatabaseService technologyDatabaseService;

  private final ResponseBodyDtoFactory responseBodyDtoFactory;

  @GetMapping
  public ResponseEntity<ResponseBodyDto<List<TechnologyResponseDto>>> getAll() {
    List<TechnologyResponseDto> technologyResponseDtoList = technologyDatabaseService.findAll();
    ResponseBodyDto<List<TechnologyResponseDto>> responseBody =
        responseBodyDtoFactory.buildResponseBody(technologyResponseDtoList, "200");

    return ResponseEntity.ok(responseBody);
  }
}
