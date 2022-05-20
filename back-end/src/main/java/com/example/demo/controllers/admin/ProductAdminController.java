package com.example.demo.controllers.admin;

import com.example.demo.dto.requests.product.AddSizeToProductRequestDto;
import com.example.demo.dto.requests.product.CreateProductRequestDto;
import com.example.demo.dto.requests.product.DeleteProductRequestDto;
import com.example.demo.dto.requests.product.UpdateProductRequestDto;
import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.responses.product.ProductResponseDto;
import com.example.demo.entities.factories.responsebodydto.ResponseBodyDtoFactory;
import com.example.demo.services.interfaces.product.ProductCrudService;
import com.example.demo.services.interfaces.productsize.ProductSizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/admin/products")
@RequiredArgsConstructor
public class ProductAdminController {
  private final ResponseBodyDtoFactory responseBodyDtoFactory;

  private final ProductCrudService productCRUDService;

  private final ProductSizeService productSizeService;

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping(path = "/create")
  public ResponseEntity<ResponseBodyDto<ProductResponseDto>> createProduct(
      @Valid @RequestBody CreateProductRequestDto createProductRequestDTO) {
    ProductResponseDto productResponseDTO =
        productCRUDService.createProduct(createProductRequestDTO);
    ResponseBodyDto<ProductResponseDto> responseBodyDTO =
        responseBodyDtoFactory.buildResponseBody(productResponseDTO, "200");

    return ResponseEntity.ok(responseBodyDTO);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping(path = "/add_size")
  public ResponseEntity<ResponseBodyDto<Object>> addSizeToProduct(
      @Valid @RequestBody AddSizeToProductRequestDto requestDto) {
    productSizeService.addSizeToProduct(requestDto);
    ResponseBodyDto<Object> responseBodyDto = responseBodyDtoFactory.buildResponseBody(null, "200");

    return ResponseEntity.ok(responseBodyDto);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping(path = "/update")
  public ResponseEntity<ResponseBodyDto<ProductResponseDto>> updateProduct(
      @Valid @RequestBody UpdateProductRequestDto requestDto) {
    ProductResponseDto productResponseDto = productCRUDService.updateProduct(requestDto);
    ResponseBodyDto<ProductResponseDto> responseBodyDto =
        responseBodyDtoFactory.buildResponseBody(productResponseDto, "200");

    return ResponseEntity.ok(responseBodyDto);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping(path = "/delete")
  public ResponseEntity<ResponseBodyDto<Object>> deleteProduct(
      @Valid @RequestBody DeleteProductRequestDto requestDto) {
    productCRUDService.deleteProduct(requestDto.getProductId());
    ResponseBodyDto<Object> responseBodyDto = responseBodyDtoFactory.buildResponseBody(null, "200");

    return ResponseEntity.ok(responseBodyDto);
  }
}
