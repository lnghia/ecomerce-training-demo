package com.example.demo.validations.interfaces;

import com.example.demo.validations.validators.UsernameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({FIELD, ANNOTATION_TYPE, TYPE_USE})
@Constraint(validatedBy = UsernameValidator.class)
public @interface ValidUsername {
  String message() default "Username has existed";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
