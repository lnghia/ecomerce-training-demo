package com.example.demo.dto.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginRequestDTO {
    @NotNull
    private String username;

    @NotNull
    private String password;
}
