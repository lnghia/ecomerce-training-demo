package com.example.demo.dto.responses;

import lombok.*;

import java.util.HashMap;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBodyDto<T> {
  String status;
  T data;
  HashMap<String, String> errors;

  public ResponseBodyDto(String status, T data) {
    this.status = status;
    this.data = data;
  }
}
