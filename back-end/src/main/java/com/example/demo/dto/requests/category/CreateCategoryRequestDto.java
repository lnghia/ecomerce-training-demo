package com.example.demo.dto.requests.category;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateCategoryRequestDto {
  @NotNull private String name;

  @NotNull private String description;
}
