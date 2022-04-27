package com.example.demo.controllers;

import com.example.demo.dto.responses.ProductResponseDTO;
import com.example.demo.dto.responses.ResponseBodyDTO;
import com.example.demo.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<ResponseBodyDTO> getProduct(@RequestParam Long id) {
        ProductResponseDTO productResponseDTO = productService.findById(id);
        ResponseBodyDTO responseBodyDTO = ResponseBodyDTO.builder().data(productResponseDTO).build();

        return ResponseEntity.ok(responseBodyDTO);
    }
}
