package com.example.demo.dto.requests.size;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SizeRequestDto {
  @NotNull @NotEmpty private String name;

  private String description;
}
