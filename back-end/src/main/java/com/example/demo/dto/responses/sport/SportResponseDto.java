package com.example.demo.dto.responses.sport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SportResponseDto {
  private Long id;

  private String name;

  private String description;
}
