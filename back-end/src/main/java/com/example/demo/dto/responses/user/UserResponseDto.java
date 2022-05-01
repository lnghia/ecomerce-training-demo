package com.example.demo.dto.responses.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private long id;

    private String firstName;

    private String lastName;

    private String email;
}
