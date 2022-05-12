package com.example.demo.services.implementations.user;

import com.example.demo.dto.responses.user.UserResponseDto;
import com.example.demo.entities.RoleEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.interfaces.user.UserRoleService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private ModelMapper modelMapper;

    @Autowired
    public UserRoleServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserResponseDto assignRoleToUser(long userId, long roleId) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        UserEntity user = userEntity.orElseThrow(() -> new UserNotFoundException());
        RoleEntity roleEntity = roleRepository.getById(roleId);

        user.getRoles().clear();
        user.getRoles().add(roleEntity);
        user = userRepository.save(user);

        return modelMapper.map(user, UserResponseDto.class);
    }
}
