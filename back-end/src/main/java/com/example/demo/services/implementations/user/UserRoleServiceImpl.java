package com.example.demo.services.implementations.user;

import com.example.demo.dto.responses.user.UserResponseDto;
import com.example.demo.entities.RoleEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.interfaces.user.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final ModelMapper modelMapper;

    @Override
    public UserResponseDto assignRoleToUser(long userId, long roleId) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        UserEntity user = userEntity.orElseThrow(UserNotFoundException::new);
        RoleEntity roleEntity = roleRepository.getById(roleId);

        user.getRoles().clear();
        user.getRoles().add(roleEntity);
        user = userRepository.save(user);

        return modelMapper.map(user, UserResponseDto.class);
    }
}
