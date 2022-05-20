package com.example.demo.controllers.admin;

import com.example.demo.dto.requests.category.CreateCategoryRequestDto;
import com.example.demo.dto.requests.category.UpdateCategoryRequestDto;
import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.responses.category.CategoryResponseDto;
import com.example.demo.entities.factories.responsebodydto.ResponseBodyDtoFactory;
import com.example.demo.services.interfaces.category.CategoryCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/admin/category")
@RequiredArgsConstructor
public class CategoryAdminController {
    private final CategoryCrudService categoryCrudService;

    private final ResponseBodyDtoFactory responseBodyDtoFactory;

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<ResponseBodyDto<CategoryResponseDto>> updateCategory(@RequestParam(name = "id") Long categoryId,
                                                                               @Valid @RequestBody UpdateCategoryRequestDto requestDto) {
        CategoryResponseDto updatedCategory = categoryCrudService.updateCategory(categoryId, requestDto);
        ResponseBodyDto<CategoryResponseDto> responseBodyDto = responseBodyDtoFactory.buildResponseBody(updatedCategory, "200");

        return ResponseEntity.ok(responseBodyDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ResponseBodyDto<CategoryResponseDto>> createCategory(@Valid @RequestBody CreateCategoryRequestDto requestDto) {
        CategoryResponseDto createdCategory = categoryCrudService.createCategory(requestDto);
        ResponseBodyDto<CategoryResponseDto> responseBodyDto = responseBodyDtoFactory.buildResponseBody(createdCategory, "200");

        return ResponseEntity.ok(responseBodyDto);
    }
}
