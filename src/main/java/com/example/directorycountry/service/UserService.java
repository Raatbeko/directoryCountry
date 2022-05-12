package com.example.directorycountry.service;


import com.example.directorycountry.dto.user.request.UserAuthRequest;
import com.example.directorycountry.dto.user.request.UserRequest;
import com.example.directorycountry.dto.user.response.UserResponse;
import com.example.directorycountry.dto.user.response.UserTokenResponse;
import com.example.directorycountry.exception.UserSignInException;

public interface UserService extends BaseService<UserResponse, UserRequest>{

    UserTokenResponse getToken(UserAuthRequest request) throws UserSignInException;
}
