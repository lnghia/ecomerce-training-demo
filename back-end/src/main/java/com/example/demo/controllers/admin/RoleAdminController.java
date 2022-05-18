package com.example.demo.controllers.admin;

import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.responses.role.RoleResponseDto;
import com.example.demo.entities.factories.responsebodydto.ResponseBodyDtoFactory;
import com.example.demo.services.interfaces.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/admin/role")
public class RoleAdminController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private ResponseBodyDtoFactory responseBodyDtoFactory;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<ResponseBodyDto> getRoles() {
        List<RoleResponseDto> roleResponseDtoList = roleService.findAll();
        ResponseBodyDto<List<RoleResponseDto>> responseBodyDto = responseBodyDtoFactory.buildResponseBody(roleResponseDtoList, "200");

        return ResponseEntity.ok(responseBodyDto);
    }
}
