package com.example.demo.controllers.admin;

import com.example.demo.dto.requests.technology.TechnologyCreateRequestDto;
import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.responses.technology.TechnologyResponseDto;
import com.example.demo.services.interfaces.technology.TechnologyCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/admin/technology")
public class TechnologyAdminController {
    @Autowired
    private TechnologyCrudService technologyCrudService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ResponseBodyDto> createTechnology(@Valid @RequestBody TechnologyCreateRequestDto createRequestDto) {
        TechnologyResponseDto createdTechnology = technologyCrudService.createTechnology(createRequestDto);
        ResponseBodyDto responseBodyDto = ResponseBodyDto.builder().status("200").data(createdTechnology).build();

        return ResponseEntity.ok(responseBodyDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<ResponseBodyDto> updateTechnology(@RequestParam(name = "id") Long technologyId,
                                                            @Valid @RequestBody TechnologyCreateRequestDto requestDto) {
        TechnologyResponseDto updatedTechnology = technologyCrudService.updateTechnology(technologyId, requestDto);
        ResponseBodyDto responseBodyDto = ResponseBodyDto.builder().status("200").data(updatedTechnology).build();

        return ResponseEntity.ok(responseBodyDto);
    }
}
