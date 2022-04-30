package com.example.demo.controllers;

import com.example.demo.dto.requests.AddSizeToProductRequestDto;
import com.example.demo.dto.requests.CreateProductRequestDto;
import com.example.demo.dto.responses.ProductResponseDto;
import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.services.interfaces.ProductCrudService;
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
    private ProductCrudService productCRUDService;

    @Autowired
    private ProductSizeService productSizeService;

    @GetMapping
    public ResponseEntity<ResponseBodyDto> getProduct(@RequestParam Long id) {
        ProductResponseDto productResponseDTO = productService.findById(id);
        ResponseBodyDto responseBodyDTO = ResponseBodyDto.builder().data(productResponseDTO).build();

        return ResponseEntity.ok(responseBodyDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<ResponseBodyDto> createProduct(@Valid @RequestBody CreateProductRequestDto createProductRequestDTO) {
        ProductResponseDto productResponseDTO = productCRUDService.createProduct(createProductRequestDTO);
        ResponseBodyDto responseBodyDTO = ResponseBodyDto.builder().data(productResponseDTO).build();

        return ResponseEntity.ok(responseBodyDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/add_size")
    public ResponseEntity<ResponseBodyDto> addSizeToProduct(@Valid @RequestBody AddSizeToProductRequestDto requestDto) {
        productSizeService.addSizeToProduct(requestDto);

        return ResponseEntity.ok(ResponseBodyDto.builder().status("200").build());
    }
}
