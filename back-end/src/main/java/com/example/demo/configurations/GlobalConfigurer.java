package com.example.demo.configurations;

import com.example.demo.entities.CustomUserDetails;
import com.example.demo.entities.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class GlobalConfigurer {
    @Bean
    AuditorAware<String> auditorProvider() {
        return new AuditorAware<>() {
            @Override
            public Optional<String> getCurrentAuditor() {
                Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

                if (obj instanceof String) {
                    return Optional.ofNullable((String) obj);
                }

                CustomUserDetails customUserDetails = (CustomUserDetails) obj;
                UserEntity userEntity = customUserDetails.getUser();
                return Optional.ofNullable(userEntity.getUsername());
            }
        };
    }
}
