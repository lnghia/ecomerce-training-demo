package com.example.demo.dto.responses.gender;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class GenderResponseDto {
  private Long id;

  private String name;
}
