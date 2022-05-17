package com.example.demo.controllers.user;

import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.responses.size.SizeResponseDto;
import com.example.demo.services.interfaces.size.SizeDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/size")
public class SizeUserController {
    @Autowired
    private SizeDatabaseService sizeDatabaseService;

    @GetMapping
    public ResponseEntity<ResponseBodyDto> getAll() {
        List<SizeResponseDto> data = sizeDatabaseService.getAll();
        ResponseBodyDto response = ResponseBodyDto.builder().data(data).build();

        return ResponseEntity.ok(response);
    }
}
