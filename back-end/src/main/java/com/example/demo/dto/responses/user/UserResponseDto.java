package com.example.demo.dto.responses.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    protected long id;

    protected String firstName;

    protected String lastName;

    protected String email;
}
