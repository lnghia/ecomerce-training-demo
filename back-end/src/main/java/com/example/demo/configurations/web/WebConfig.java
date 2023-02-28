package com.example.demo.configurations.web;

import com.example.demo.security.resolvers.UserContextHolderArgResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {

        // A resolver that creates a UserContextHolder object.
        resolvers.add(new UserContextHolderArgResolver());
    }
}
