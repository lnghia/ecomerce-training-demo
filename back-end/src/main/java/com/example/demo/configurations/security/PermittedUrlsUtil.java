package com.example.demo.configurations.security;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PermittedUrlsUtil {
  private final ArrayList<String> protectedUrlPatterns =
      new ArrayList<>(
          List.of(
              "/api/users/edit_info",
              "/api/admin/",
              "/api/auth/refresh_tokens",
              "/api/product/rate_product",
              "/api/auth/test",
              "/api/product/user_review_on_product"));

  public boolean isPermitted(String url) {
    for (var pattern : protectedUrlPatterns) {
      if (url.contains(pattern)) {
        return false;
      }
    }

    return true;
  }
}
