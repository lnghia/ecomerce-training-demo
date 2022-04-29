package com.example.demo.controllers;

import com.example.demo.dto.requests.AddSizeToProductRequestDto;
import com.example.demo.dto.requests.CreateProductRequestDTO;
import com.example.demo.dto.responses.ProductResponseDTO;
import com.example.demo.dto.responses.ResponseBodyDTO;
import com.example.demo.services.interfaces.ProductCRUDService;
import com.example.demo.services.interfaces.ProductService;
import com.example.demo.services.interfaces.ProductSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCRUDService productCRUDService;

    @Autowired
    private ProductSizeService productSizeService;

    @GetMapping
    public ResponseEntity<ResponseBodyDTO> getProduct(@RequestParam Long id) {
        ProductResponseDTO productResponseDTO = productService.findById(id);
        ResponseBodyDTO responseBodyDTO = ResponseBodyDTO.builder().data(productResponseDTO).build();

        return ResponseEntity.ok(responseBodyDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "/create")
    public ResponseEntity<ResponseBodyDTO> createProduct(@Valid @RequestBody CreateProductRequestDTO createProductRequestDTO) {
        ProductResponseDTO productResponseDTO = productCRUDService.createProduct(createProductRequestDTO);
        ResponseBodyDTO responseBodyDTO = ResponseBodyDTO.builder().data(productResponseDTO).build();

        return ResponseEntity.ok(responseBodyDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/add_size")
    public ResponseEntity<ResponseBodyDTO> addSizeToProduct(@Valid @RequestBody AddSizeToProductRequestDto requestDto) {
        productSizeService.addSizeToProduct(requestDto);

        return ResponseEntity.ok(ResponseBodyDTO.builder().status("200").build());
    }
}
