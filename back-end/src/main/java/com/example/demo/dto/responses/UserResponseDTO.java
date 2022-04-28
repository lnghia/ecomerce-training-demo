package com.example.demo.dto.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    private long id;

    private String firstName;

    private String lastName;

    private String email;
}
