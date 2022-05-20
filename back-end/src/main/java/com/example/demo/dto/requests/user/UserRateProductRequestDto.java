package com.example.demo.dto.requests.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRateProductRequestDto {
  private Long productId;

  private String comment;

  private int rating;

  private int page = 0;

  private int size = 3;
}
