package com.example.demo.dto.responses.user;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageableUserRateProductResponseDto {
  private Long totalElements;

  private int totalPages;

  private int size;

  private List<UserRateProductResponseDto> content;
}
