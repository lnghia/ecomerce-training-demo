package com.example.demo.configurations.security;

import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class PermittedUrlsUtil {
    private HashSet<String> permittedUrls = new HashSet<>(){
        {
            add("/api/auth/login");
            add("/api/auth/register");
            add("/api/auth/refresh_tokens");
            add("/api/product/search");
        }
    };

    public boolean isPermitted(String url){
        return permittedUrls.contains(url);
    }
}
