package com.example.demo.utilities.authentication;

import com.example.demo.entities.CustomUserDetails;
import com.example.demo.entities.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationUtility {
  @Bean
  public UserEntity getUserDetailFromSecurityContext() {
    UserEntity userEntity = null;

    if (SecurityContextHolder.getContext() != null
        && SecurityContextHolder.getContext().getAuthentication() != null) {
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

      if (principal instanceof CustomUserDetails) {
        userEntity = ((CustomUserDetails) principal).getUser();
      }
    }

    return userEntity;
  }
}
