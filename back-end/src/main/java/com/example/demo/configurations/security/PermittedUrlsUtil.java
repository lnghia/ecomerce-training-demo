package com.example.demo.configurations.security;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class PermittedUrlsUtil {
    private ArrayList<String> protectedUrlPatterns = new ArrayList<String>(
            List.of(
                    "/api/admin/",
                    "/api/auth/refresh_tokens",
//                    "/api/auth/assign_role",
                    "/api/product/rate_product",
                    "/api/auth/test",
                    "/api/product/user_review_on_product"
//                    "/api/admin/users"
//                    "/api/admin/roles"
//                    "/api/product/update"
//                    "/api/product/create"
//                    "/api/product/delete"
            )
    );

    private HashSet<String> permittedUrls = new HashSet<>() {
        {
            add("/api/auth/login");
            add("/api/auth/register");
            add("/api/auth/refresh_tokens");
            add("/api/product/search");
            add("/api/category");
            add("/api/sport");
            add("/api/technology");
            add("/api/gender");
        }
    };

    public boolean isPermitted(String url) {
        for (var pattern : protectedUrlPatterns) {
            if (url.contains(pattern)) {
                return false;
            }
        }

        return true;
    }
}
