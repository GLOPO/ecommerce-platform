package com.glory.userservice.service;

import com.glory.userservice.dto.request.LoginRequest;
import com.glory.userservice.dto.request.UserRegistrationRequest;
import com.glory.userservice.dto.response.LoginResponse;
import com.glory.userservice.dto.response.UserResponse;

public interface UserService {

    UserResponse register(UserRegistrationRequest request);

    LoginResponse login(LoginRequest request);
}
