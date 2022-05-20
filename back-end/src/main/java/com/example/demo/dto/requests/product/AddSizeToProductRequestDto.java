package com.example.demo.dto.requests.product;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AddSizeToProductRequestDto {
  @NotNull private Long productId;

  @NotNull private List<ProductSizeDto> productSizeDto;
}
