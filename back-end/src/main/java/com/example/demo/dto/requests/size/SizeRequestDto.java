package com.example.demo.dto.requests.size;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SizeRequestDto {
  @NotNull @NotEmpty private String name;

  private String description;
}
