package com.example.demo.controllers;

import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.responses.technology.TechnologyResponseDto;
import com.example.demo.services.interfaces.technology.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/technology")
public class TechnologyController {
    @Autowired
    private TechnologyService technologyService;

    @GetMapping
    public ResponseEntity<ResponseBodyDto> getAll() {
        List<TechnologyResponseDto> technologyResponseDtoList = technologyService.findAll();
        ResponseBodyDto responseBody = ResponseBodyDto.builder().data(technologyResponseDtoList).build();

        return ResponseEntity.ok(responseBody);
    }
}
