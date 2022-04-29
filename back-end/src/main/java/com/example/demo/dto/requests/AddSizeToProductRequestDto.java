package com.example.demo.dto.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Getter
@Setter
public class AddSizeToProductRequestDto {
    @NotNull
    private Long productId;

    @NotNull
    private ArrayList<ProductSizeDto> productSizeDto;
}
