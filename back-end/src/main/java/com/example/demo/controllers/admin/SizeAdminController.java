package com.example.demo.controllers.admin;

import com.example.demo.dto.requests.size.SizeRequestDto;
import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.responses.size.SizeResponseDto;
import com.example.demo.entities.factories.responsebodydto.ResponseBodyDtoFactory;
import com.example.demo.services.interfaces.size.SizeCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/admin/size")
public class SizeAdminController {
    @Autowired
    private SizeCrudService sizeCrudService;

    @Autowired
    private ResponseBodyDtoFactory responseBodyDtoFactory;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ResponseBodyDto> createSize(@Valid @RequestBody SizeRequestDto requestDto) {
        SizeResponseDto responseDto = sizeCrudService.createSize(requestDto);
        ResponseBodyDto<SizeResponseDto> responseBodyDto = responseBodyDtoFactory.buildResponseBody(responseDto, "200");

        return ResponseEntity.ok(responseBodyDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<ResponseBodyDto> updateSize(@RequestParam(name = "id") Long sizeId,
                                                      @Valid @RequestBody SizeRequestDto requestDto) {
        SizeResponseDto responseDto = sizeCrudService.updateSize(sizeId, requestDto);
        ResponseBodyDto<SizeResponseDto> responseBodyDto = responseBodyDtoFactory.buildResponseBody(responseDto, "200");

        return ResponseEntity.ok(responseBodyDto);
    }
}
