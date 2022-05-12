package com.example.directorycountry.mapper;

import com.example.directorycountry.dto.user.request.UserAuthRequest;
import com.example.directorycountry.dto.user.request.UserRequest;
import com.example.directorycountry.dto.user.response.UserResponse;
import com.example.directorycountry.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    

    UserResponse toUserResponseDto(UserEntity user);

    List<UserResponse> toUsersResponseDto(List<UserEntity> users);

    UserAuthRequest toUserAuthDto(UserEntity user);

    UserEntity toUserEntity(UserResponse userResponse);

    default void test(UserRequest userRequest){

    }
}