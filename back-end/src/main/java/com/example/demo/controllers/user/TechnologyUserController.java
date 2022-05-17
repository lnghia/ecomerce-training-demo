package com.example.demo.controllers.user;

import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.responses.technology.TechnologyResponseDto;
import com.example.demo.services.interfaces.technology.TechnologyDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/technology")
public class TechnologyUserController {
    @Autowired
    private TechnologyDatabaseService technologyDatabaseService;

    @GetMapping
    public ResponseEntity<ResponseBodyDto> getAll() {
        List<TechnologyResponseDto> technologyResponseDtoList = technologyDatabaseService.findAll();
        ResponseBodyDto responseBody = ResponseBodyDto.builder().data(technologyResponseDtoList).build();

        return ResponseEntity.ok(responseBody);
    }
}
