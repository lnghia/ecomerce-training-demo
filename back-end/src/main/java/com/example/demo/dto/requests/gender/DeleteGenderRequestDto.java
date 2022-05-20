package com.example.demo.dto.requests.gender;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DeleteGenderRequestDto {
  @NotNull private Long genderId;
}
