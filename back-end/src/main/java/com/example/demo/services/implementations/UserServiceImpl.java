package com.example.demo.services.implementations;

import com.example.demo.dto.responses.UserResponseDto;
import com.example.demo.entities.RoleEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.exceptions.UserExistedException;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.interfaces.UserService;
import com.example.demo.utilities.wrapper.RoleUtilityWrapper;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
@NoArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private RoleUtilityWrapper roleUtilityWrapper;

    private PasswordEncoder passwordEncoder;

    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, RoleUtilityWrapper roleUtilityWrapper, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.roleUtilityWrapper = roleUtilityWrapper;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserEntity getUserById(long id) {
        Optional<UserEntity> user = userRepository.findById(id);

        return user.orElse(null);
    }

    @Override
    public UserEntity getUserByUsername(String username) {
        Optional<UserEntity> user = userRepository.findByUsername(username);

        return user.orElse(null);
    }

    @Override
    public UserResponseDto createNormalUser(UserEntity newUser) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(newUser.getEmail());

        if (userEntity.isPresent()) {
            throw new UserExistedException();
        }

        String plainPassword = newUser.getPassword();
        String encryptedPassword = passwordEncoder.encode(plainPassword);
        Optional<RoleEntity> roleEntity = roleRepository.findByName(roleUtilityWrapper.getUserRoleString());

        newUser.setPassword(encryptedPassword);
        newUser.setUsername(newUser.getEmail());
        newUser.setRoles(Set.of(roleEntity.get()));

        return modelMapper.map(userRepository.save(newUser), UserResponseDto.class);
    }


    @Override
    public Collection<RoleEntity> getUserGrantedPermissions(long id) {
        Optional<UserEntity> user = userRepository.findById(id);

        return user.get().getRoles();
    }

    @Override
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public boolean hasUserExisted(long id) {
        Optional<UserEntity> user = userRepository.findById(id);

        return user.isPresent();
    }

    @Override
    public boolean hasUserExisted(String email) {
        Optional<UserEntity> user = userRepository.findByUsername(email);

        return user.isPresent();
    }
}
