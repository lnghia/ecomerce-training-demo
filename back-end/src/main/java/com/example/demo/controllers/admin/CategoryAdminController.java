package com.example.demo.controllers.admin;

import com.example.demo.dto.requests.category.CreateCategoryRequestDto;
import com.example.demo.dto.requests.category.UpdateCategoryRequestDto;
import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.responses.category.CategoryResponseDto;
import com.example.demo.services.interfaces.category.CategoryCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/admin/category")
public class CategoryAdminController {
    @Autowired
    private CategoryCrudService categoryCrudService;

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<ResponseBodyDto> updateCategory(@RequestParam(name = "id") Long categoryId,
                                                          @Valid @RequestBody UpdateCategoryRequestDto requestDto) {
        CategoryResponseDto updatedCategory = categoryCrudService.updateCategory(categoryId, requestDto);
        ResponseBodyDto responseBodyDto = ResponseBodyDto.builder().status("200").data(updatedCategory).build();

        return ResponseEntity.ok(responseBodyDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ResponseBodyDto> createCategory(@Valid @RequestBody CreateCategoryRequestDto requestDto) {
        CategoryResponseDto createdCategory = categoryCrudService.createCategory(requestDto);
        ResponseBodyDto responseBodyDto = ResponseBodyDto.builder().status("200").data(createdCategory).build();

        return ResponseEntity.ok(responseBodyDto);
    }
}
