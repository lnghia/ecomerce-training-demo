package com.example.demo.controllers.user;

import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.responses.category.CategoryResponseDto;
import com.example.demo.services.interfaces.category.CategoryCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/category")
public class CategoryUserController {
    @Autowired
    private CategoryCrudService categoryCrudService;

    @GetMapping
    public ResponseEntity<ResponseBodyDto> getAll() {
        List<CategoryResponseDto> categoryResponseDtoList = categoryCrudService.findAll();
        ResponseBodyDto responseBody = ResponseBodyDto.builder().data(categoryResponseDtoList).build();

        return ResponseEntity.ok(responseBody);
    }
}
