package com.example.demo.dto.responses.size;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
public class SizeResponseDto {
  private Long id;

  private String description;

  private String name;
}
