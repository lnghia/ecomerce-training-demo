package com.example.demo.dto.requests;

import com.example.demo.validations.interfaces.ValidRoleId;
import com.example.demo.validations.interfaces.ValidUserId;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AssignRoleToUserRequestDTO {
    @NotNull
    @ValidUserId
    private Long userId;

    @NotNull
    @ValidRoleId
    private Long roleId;
}
