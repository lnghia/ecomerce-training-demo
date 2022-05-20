package com.example.demo.validations.validators;

import com.example.demo.services.interfaces.user.UserService;
import com.example.demo.validations.interfaces.ValidUserId;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserIdValidator implements ConstraintValidator<ValidUserId, Long> {
  @Autowired private UserService userService;

  @Override
  public boolean isValid(Long userId, ConstraintValidatorContext constraintValidatorContext) {
    return userId != null && userService.hasUserExisted(userId);
  }
}
