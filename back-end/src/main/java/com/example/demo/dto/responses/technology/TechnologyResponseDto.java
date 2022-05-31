package com.example.demo.dto.responses.technology;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TechnologyResponseDto {
  private Long id;

  private String name;

  private String description;
}
