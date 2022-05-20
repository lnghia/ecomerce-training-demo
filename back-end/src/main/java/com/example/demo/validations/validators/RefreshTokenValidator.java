package com.example.demo.validations.validators;

import com.example.demo.security.providers.JWTProvider;
import com.example.demo.validations.interfaces.ValidRefreshToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class RefreshTokenValidator implements ConstraintValidator<ValidRefreshToken, String> {
  @Autowired private JWTProvider jwtProvider;

  @Override
  public boolean isValid(
      String refreshToken, ConstraintValidatorContext constraintValidatorContext) {
    return refreshToken != null
        && !refreshToken.isEmpty()
        && jwtProvider.validateToken(refreshToken);
  }
}
