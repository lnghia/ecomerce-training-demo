package com.example.demo.dto.responses.technology;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TechnologyResponseDto {
  private Long id;

  private String name;

  private String description;
}
