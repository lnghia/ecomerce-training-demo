package com.example.demo.email.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailToSend {
  String title;

  String content;

  String receiver;
}
