package com.example.demo.controllers.admin;

import com.example.demo.dto.requests.product.AddSizeToProductRequestDto;
import com.example.demo.dto.requests.product.CreateProductRequestDto;
import com.example.demo.dto.requests.product.DeleteProductRequestDto;
import com.example.demo.dto.requests.product.UpdateProductRequestDto;
import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.responses.product.ProductResponseDto;
import com.example.demo.services.interfaces.product.ProductCrudService;
import com.example.demo.services.interfaces.product.ProductService;
import com.example.demo.services.interfaces.product.ProductSizeService;
import com.example.demo.services.interfaces.user.UserService;
import com.example.demo.utilities.authentication.AuthenticationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/admin/products")
public class ProductAdminController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCrudService productCRUDService;

    @Autowired
    private ProductSizeService productSizeService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationUtility authenticationUtility;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "/create")
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
    @PutMapping(path = "/update")
    public ResponseEntity<ResponseBodyDto> updateProduct(@Valid @RequestBody UpdateProductRequestDto requestDto) {
        ProductResponseDto productResponseDto = productCRUDService.updateProduct(requestDto);

        return ResponseEntity.ok(ResponseBodyDto.builder().status("200").data(productResponseDto).build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/delete")
    public ResponseEntity<ResponseBodyDto> deleteProduct(@Valid @RequestBody DeleteProductRequestDto requestDto) {
        Boolean deleteSuccess = productCRUDService.deleteProduct(requestDto.getProductId());

        return ResponseEntity.ok(ResponseBodyDto.builder().status("200").build());
    }
}
