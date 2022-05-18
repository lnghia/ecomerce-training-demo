package com.example.demo.controllers.user;

import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.responses.sport.SportResponseDto;
import com.example.demo.entities.factories.responsebodydto.ResponseBodyDtoFactory;
import com.example.demo.services.interfaces.sport.SportDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/sport")
public class SportUserController {
    @Autowired
    private SportDatabaseService sportDatabaseService;

    @Autowired
    private ResponseBodyDtoFactory responseBodyDtoFactory;

    @GetMapping
    public ResponseEntity<ResponseBodyDto> getAll() {
        List<SportResponseDto> sportResponseDtoList = sportDatabaseService.findAll();
        ResponseBodyDto<List<SportResponseDto>> responseBody = responseBodyDtoFactory.buildResponseBody(sportResponseDtoList, "200");

        return ResponseEntity.ok(responseBody);
    }
}
