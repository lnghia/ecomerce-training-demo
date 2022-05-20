package com.example.demo.dto.requests.product;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DeleteProductRequestDto {
  @NotNull private Long productId;
}
