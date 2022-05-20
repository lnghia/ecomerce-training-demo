package com.example.demo.controllers.admin;

import com.example.demo.dto.requests.sport.CreateSportRequestDto;
import com.example.demo.dto.requests.sport.UpdateSportRequestDto;
import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.responses.sport.SportResponseDto;
import com.example.demo.entities.factories.responsebodydto.ResponseBodyDtoFactory;
import com.example.demo.services.interfaces.sport.SportCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/admin/sport")
@RequiredArgsConstructor
public class SportAdminController {
    private final SportCrudService sportCrudService;

    private final ResponseBodyDtoFactory responseBodyDtoFactory;

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<ResponseBodyDto<SportResponseDto>> updateSport(@RequestParam(name = "id") Long sportId,
                                                                         @Valid @RequestBody UpdateSportRequestDto requestDto) {
        SportResponseDto updatedSport = sportCrudService.updateSport(sportId, requestDto);
        ResponseBodyDto<SportResponseDto> responseBodyDto = responseBodyDtoFactory.buildResponseBody(updatedSport, "200");

        return ResponseEntity.ok(responseBodyDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ResponseBodyDto<SportResponseDto>> createSport(@Valid @RequestBody CreateSportRequestDto requestDto) {
        SportResponseDto createdSport = sportCrudService.createSport(requestDto);
        ResponseBodyDto<SportResponseDto> responseBodyDto = responseBodyDtoFactory.buildResponseBody(createdSport, "200");

        return ResponseEntity.ok(responseBodyDto);
    }
}
