package com.example.demo.services.implementations.user;

import com.example.demo.dto.requests.user.UserRateProductRequestDto;
import com.example.demo.dto.responses.user.UserListResponseDto;
import com.example.demo.dto.responses.user.UserRateProductResponseDto;
import com.example.demo.dto.responses.user.UserResponseDto;
import com.example.demo.entities.ProductEntity;
import com.example.demo.entities.RoleEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.entities.UserRateProductEntity;
import com.example.demo.exceptions.UserExistedException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRateProductRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.interfaces.product.ProductCrudService;
import com.example.demo.services.interfaces.user.UserService;
import com.example.demo.utilities.wrapper.RoleUtilityWrapper;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private RoleUtilityWrapper roleUtilityWrapper;

    private PasswordEncoder passwordEncoder;

    private ProductCrudService productCrudService;

    private UserRateProductRepository userRateProductRepository;

    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRateProductRepository userRateProductRepository,
                           UserRepository userRepository,
                           ProductCrudService productCrudService,
                           RoleRepository roleRepository,
                           RoleUtilityWrapper roleUtilityWrapper,
                           PasswordEncoder passwordEncoder,
                           ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.roleUtilityWrapper = roleUtilityWrapper;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.productCrudService = productCrudService;
        this.userRateProductRepository = userRateProductRepository;
    }

    @Override
    public UserEntity getUserById(long id) {
        Optional<UserEntity> user = userRepository.findById(id);

        return user.orElse(null);
    }

    @Override
    public UserEntity findById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);

        if (user.isPresent()) {
            return user.get();
        }

        throw new UserNotFoundException();
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

    @Override
    @Transactional
    public UserRateProductResponseDto rateProduct(UserRateProductRequestDto requestDto, UserEntity userEntity) {
        Long productId = requestDto.getProductId();
        int rating = requestDto.getRating();
        String comment = requestDto.getComment();

        ProductEntity product = productCrudService.findById(productId);
        double averageRating = product.getAverageRating();
        averageRating = (averageRating * product.getCountRating() + requestDto.getRating()) / (product.getCountRating() + 1);
        product.setCountRating(product.getCountRating() + 1);
        product.setAverageRating(averageRating);

        UserRateProductEntity userRateProductEntity = UserRateProductEntity.builder()
                .rating(rating)
                .comment(comment)
                .product(product)
                .user(userEntity)
                .build();

        userRateProductEntity = userRateProductRepository.save(userRateProductEntity);
        productCrudService.saveProduct(product);

        return modelMapper.map(userRateProductEntity, UserRateProductResponseDto.class);
    }

    @Override
    public UserResponseDto activeUser(Long userId) {
        return this.changeStatusActive(userId, true);
    }

    @Override
    public UserResponseDto deActiveUser(Long userId) {
        return this.changeStatusActive(userId, false);
    }

    @Override
    @Transactional
    public UserResponseDto changeStatusActive(Long userId, boolean status) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
        user.setIsActive(status);
        user = userRepository.save(user);
        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public List<UserListResponseDto> getListUser() {
        return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserListResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<UserListResponseDto> getListNormalUser() {
        List<UserEntity> userEntities = userRepository.findAllWithStatus(true);

        List<UserListResponseDto> result = userEntities.stream().map(userEntity -> {
            return modelMapper.map(userEntity, UserListResponseDto.class);
        }).collect(Collectors.toList());

        return result;
    }

    @Override
    public List<UserListResponseDto> getListBlockedUser() {
        List<UserEntity> userEntities = userRepository.findAllWithStatus(false);

        List<UserListResponseDto> result = userEntities.stream().map(userEntity -> {
            return modelMapper.map(userEntity, UserListResponseDto.class);
        }).collect(Collectors.toList());

        return result;
    }
}
