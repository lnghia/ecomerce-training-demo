package com.example.demo.controllers;

import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.responses.sport.SportResponseDto;
import com.example.demo.services.interfaces.sport.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/sport")
public class SportController {
    @Autowired
    private SportService sportService;

    @GetMapping
    public ResponseEntity<ResponseBodyDto> getAll() {
        List<SportResponseDto> sportResponseDtoList = sportService.findAll();
        ResponseBodyDto responseBody = ResponseBodyDto.builder().data(sportResponseDtoList).build();

        return ResponseEntity.ok(responseBody);
    }
}
