package com.example.demo.controllers;

import com.example.demo.dto.requests.sport.CreateSportRequestDto;
import com.example.demo.dto.requests.sport.UpdateSportRequestDto;
import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.responses.sport.SportResponseDto;
import com.example.demo.services.interfaces.sport.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PutMapping
    public ResponseEntity<ResponseBodyDto> updateSport(@RequestParam(name = "id") Long sportId,
                                                       @Valid @RequestBody UpdateSportRequestDto requestDto) {
        SportResponseDto updatedSport = sportService.updateSport(requestDto);
        ResponseBodyDto responseBodyDto = ResponseBodyDto.builder().status("200").data(updatedSport).build();

        return ResponseEntity.ok(responseBodyDto);
    }

    @PostMapping
    public ResponseEntity<ResponseBodyDto> createSport(@Valid @RequestBody CreateSportRequestDto requestDto) {
        SportResponseDto createdSport = sportService.createSport(requestDto);
        ResponseBodyDto responseBodyDto = ResponseBodyDto.builder().status("200").data(createdSport).build();

        return ResponseEntity.ok(responseBodyDto);
    }
}
