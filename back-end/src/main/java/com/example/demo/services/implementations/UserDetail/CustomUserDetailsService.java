package com.example.demo.services.implementations.UserDetail;

import com.example.demo.entities.CustomUserDetails;
import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByUsername(username);

        if (user.isEmpty()) throw new UsernameNotFoundException(username);

        return new CustomUserDetails(user.get());
    }
}
