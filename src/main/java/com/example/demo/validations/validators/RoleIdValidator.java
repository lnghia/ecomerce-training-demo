package com.example.demo.validations.validators;

import com.example.demo.services.interfaces.RoleService;
import com.example.demo.validations.interfaces.ValidRoleId;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RoleIdValidator implements ConstraintValidator<ValidRoleId, Long> {
    @Autowired
    private RoleService roleService;

    @Override
    public boolean isValid(Long roleId, ConstraintValidatorContext constraintValidatorContext) {
        return roleId != null && roleService.hasRoleExisted(roleId);
    }
}
