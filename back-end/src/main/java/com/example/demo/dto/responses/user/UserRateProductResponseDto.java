package com.example.demo.dto.responses.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRateProductResponseDto {
    private UserResponseDto user;

    private int rating;

    private String comment;

    private String lastModifiedDate;
}
