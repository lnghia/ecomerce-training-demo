package com.example.demo.dto.requests.technology;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TechnologyCreateRequestDto {
  @NotNull @NotEmpty private String name;

  @NotNull @NotEmpty private String description;
}
