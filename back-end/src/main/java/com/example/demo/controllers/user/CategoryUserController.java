package com.example.demo.controllers.user;

import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.responses.category.CategoryResponseDto;
import com.example.demo.entities.factories.responsebodydto.ResponseBodyDtoFactory;
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

    @Autowired
    private ResponseBodyDtoFactory responseBodyDtoFactory;

    @GetMapping
    public ResponseEntity<ResponseBodyDto<List<CategoryResponseDto>>> getAll() {
        List<CategoryResponseDto> categoryResponseDtoList = categoryCrudService.getAll();
        ResponseBodyDto<List<CategoryResponseDto>> responseBody = responseBodyDtoFactory.buildResponseBody(categoryResponseDtoList);

        return ResponseEntity.ok(responseBody);
    }
}
