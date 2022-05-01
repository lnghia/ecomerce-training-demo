package com.example.demo.controllers;

import com.example.demo.dto.requests.product.AddSizeToProductRequestDto;
import com.example.demo.dto.requests.product.CreateProductRequestDto;
import com.example.demo.dto.requests.product.FilterProductsRequestDto;
import com.example.demo.dto.requests.product.UpdateProductRequestDto;
import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.responses.product.ProductResponseDto;
import com.example.demo.services.interfaces.product.ProductCrudService;
import com.example.demo.services.interfaces.product.ProductService;
import com.example.demo.services.interfaces.product.ProductSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    @PostMapping
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

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<ResponseBodyDto> updateProduct(@Valid @RequestBody UpdateProductRequestDto requestDto) {
        ProductResponseDto productResponseDto = productCRUDService.updateProduct(requestDto);

        return ResponseEntity.ok(ResponseBodyDto.builder().status("200").data(productResponseDto).build());
    }

    @PostMapping(path = "/search")
    public ResponseEntity<ResponseBodyDto> getProducts(@Valid @RequestBody FilterProductsRequestDto requestDto) {
        int size = requestDto.getSize();
        int page = requestDto.getPage();
        String name = requestDto.getName();
        Long genderId = requestDto.getGenderId();
        Long sportId = requestDto.getSportId();
        List<Long> categoryIds = requestDto.getCategoryIds();
        List<Long> technologyIds = requestDto.getTechnologyIds();
        String sortType = requestDto.getSortType();
        String sortBy = requestDto.getSortBy();

        List<ProductResponseDto> products = productService.findAllWithFilterAndSort(
                categoryIds,
                genderId,
                sportId,
                technologyIds,
                name,
                page,
                size,
                sortType,
                sortBy
        );
        ResponseBodyDto responseBodyDto = ResponseBodyDto.builder().status("200").data(products).build();

        return ResponseEntity.ok(responseBodyDto);
    }
}
