package com.example.demo.controllers.user;

import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.responses.gender.GenderResponseDto;
import com.example.demo.services.interfaces.gender.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/gender")
public class GenderUserController {
    @Autowired
    private GenderService genderService;

    @GetMapping
    public ResponseEntity<ResponseBodyDto> getAll() {
        List<GenderResponseDto> genderResponseDtoList = genderService.findAll();
        ResponseBodyDto responseData = ResponseBodyDto.builder().data(genderResponseDtoList).build();

        return ResponseEntity.ok(responseData);
    }
}
