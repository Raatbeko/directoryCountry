package com.example.directorycountry.service.impl;

import com.example.directorycountry.dto.user.request.UserAuthRequest;
import com.example.directorycountry.dto.user.request.UserRequest;
import com.example.directorycountry.dto.user.response.UserResponse;
import com.example.directorycountry.dto.user.response.UserTokenResponse;
import com.example.directorycountry.entity.UserEntity;
import com.example.directorycountry.entity.UserRoleEntity;
import com.example.directorycountry.exception.EmailNotBeEmptyException;
import com.example.directorycountry.exception.UserSignInException;
import com.example.directorycountry.mapper.UserMapper;
import com.example.directorycountry.repository.RoleRepository;
import com.example.directorycountry.repository.UserRepository;
import com.example.directorycountry.repository.UserRoleRepository;
import com.example.directorycountry.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    final UserRepository userRepository;

    final RoleRepository roleRepository;

    final PasswordEncoder passwordEncoder;

    final UserRoleRepository userRoleRepository;

    @Override
    public UserResponse save(UserRequest t) {
        if (t.getEmail() == null)
            throw new EmailNotBeEmptyException("email is empty", HttpStatus.valueOf("EMAIL_NOT_BE_EMPTY"));
        UserEntity userEntity = userRepository
                .save(UserEntity.builder()
                        .login(t.getLogin())
                        .email(t.getEmail())
                        .password(passwordEncoder.encode(t.getPassword()))
                        .isActive(true)
                        .build());
        userRoleRepository
                .save(UserRoleEntity.builder()
                        .role(roleRepository.getById(1L))
                        .user(userEntity).build());


        return UserResponse.builder()
                .email(userEntity.getEmail())
                .id(userEntity.getId())
                .login(userEntity.getLogin())
                .build();
    }

    @Override
    public UserTokenResponse getToken(UserAuthRequest request) throws UserSignInException {
        UserEntity userEntity = userRepository.findByUserNameAndEMail(request.getLoginOrEmail());
        boolean isMatches = passwordEncoder.matches(request.getPassword(), userEntity.getPassword());
        if (isMatches) {
            String token = "Basic " + new String(Base64.getEncoder()
                    .encode((userEntity.getLogin() + ":" + request.getPassword()).getBytes()));
            return UserTokenResponse
                    .builder()
                    .userToken(token)
                    .build();
        } else {
            throw new UserSignInException("Неправильный логин или пароль!", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<UserResponse> getAll() {
        return UserMapper.INSTANCE.toUsersResponseDto(userRepository.findAll());
    }

    @Override
    public UserResponse findById(Long id) {
        return UserMapper.INSTANCE.toUserResponseDto(userRepository.getById(id));
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

}
