package com.example.demo.controllers.user;

import com.example.demo.dto.requests.product.FilterProductsRequestDto;
import com.example.demo.dto.requests.product.PageableProductListResponseDto;
import com.example.demo.dto.requests.user.UserRateProductRequestDto;
import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.responses.product.ProductResponseDto;
import com.example.demo.dto.responses.user.PageableUserRateProductResponseDto;
import com.example.demo.dto.responses.user.UserRateProductResponseDto;
import com.example.demo.entities.CustomUserDetails;
import com.example.demo.entities.UserEntity;
import com.example.demo.services.interfaces.product.ProductCrudService;
import com.example.demo.services.interfaces.product.ProductService;
import com.example.demo.services.interfaces.product.ProductSizeService;
import com.example.demo.services.interfaces.user.UserService;
import com.example.demo.utilities.authentication.AuthenticationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/product")
public class ProductUserController {
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

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseBodyDto> getProduct(@PathVariable Long id) {
        ProductResponseDto productResponseDTO = productService.findById(id);
        ResponseBodyDto responseBodyDTO = ResponseBodyDto.builder().data(productResponseDTO).build();

        return ResponseEntity.ok(responseBodyDTO);
    }

    @PostMapping(path = "/search")
    public ResponseEntity<ResponseBodyDto> getProducts(@Valid @RequestBody FilterProductsRequestDto requestDto) {
        int size = requestDto.getSize();
        int page = requestDto.getPage();
        String name = requestDto.getName();
        List<Long> genderId = requestDto.getGenderIds();
        List<Long> sportId = requestDto.getSportIds();
        List<Long> categoryIds = requestDto.getCategoryIds();
        List<Long> technologyIds = requestDto.getTechnologyIds();
        String sortType = requestDto.getSortType();
        String sortBy = requestDto.getSortBy();

        PageableProductListResponseDto products = productService.findAllWithFilterAndSort(
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

    @GetMapping(path = "/all")
    public ResponseEntity<ResponseBodyDto> findAllProduct() {
        List<ProductResponseDto> result = productService.getAll();
        ResponseBodyDto response = ResponseBodyDto.builder().status("200").data(result).build();

        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/rate_product")
    public ResponseEntity<ResponseBodyDto> rateProduct(@Valid @RequestBody UserRateProductRequestDto requestDto,
                                                       @RequestParam Long productId) {
        UserEntity userEntity = authenticationUtility.getUserDetailFromSecurityContext();
        requestDto.setProductId(productId);
        UserRateProductResponseDto responseDto = userService.rateProduct(requestDto, userEntity);
        ResponseBodyDto responseBody = ResponseBodyDto.builder().data(responseDto).build();

        return ResponseEntity.ok(responseBody);
    }

    @GetMapping(path = "/ratings")
    public ResponseEntity<ResponseBodyDto> getProductRatings(@RequestParam(value = "productId") Long productId,
                                                             @RequestParam(value = "page", defaultValue = "0") int page,
                                                             @RequestParam(value = "size", defaultValue = "3") int size) {
        PageableUserRateProductResponseDto result = productService.getLatestCommentUserProduct(productId, page, size);
        ResponseBodyDto responseBodyDto = ResponseBodyDto.builder().status("200").data(result).build();

        return ResponseEntity.ok(responseBodyDto);
    }

    @GetMapping(path = "/user_review_on_product")
    public ResponseEntity<ResponseBodyDto> getUserReviewOnProduct(@RequestParam(value = "productId") Long productId) {
        UserEntity userEntity = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        UserRateProductResponseDto result = productService.findReviewOfUserOnProduct(userEntity, productId);
        ResponseBodyDto responseBody = ResponseBodyDto.builder().data(result).status("200").build();

        return ResponseEntity.ok(responseBody);
    }
}
