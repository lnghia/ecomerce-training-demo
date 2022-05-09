package com.example.demo.services.interfaces.product;

import com.example.demo.dto.requests.product.PageableProductListResponseDto;
import com.example.demo.dto.responses.product.ProductResponseDto;
import com.example.demo.dto.responses.user.PageableUserRateProductResponseDto;
import com.example.demo.dto.responses.user.UserRateProductResponseDto;
import com.example.demo.entities.UserEntity;

import java.util.List;

public interface ProductService {
    ProductResponseDto findById(long id);

    PageableProductListResponseDto findAllWithFilterAndSort(List<Long> categoryIds,
                                                            List<Long> genderIds,
                                                            List<Long> sportIds,
                                                            List<Long> technologyIds,
                                                            String name,
                                                            int page,
                                                            int size,
                                                            String sortType,
                                                            String sortBy);

    PageableUserRateProductResponseDto getLatestCommentUserProduct(Long productId, int page, int size);

    UserRateProductResponseDto findReviewOfUserOnProduct(UserEntity userEntity, Long productId);
}
