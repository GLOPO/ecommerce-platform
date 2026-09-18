package com.glory.userservice.service;

import com.glory.userservice.dto.request.UserRegistrationRequest;
import com.glory.userservice.dto.response.UserResponse;

public interface UserService {

    UserResponse register(UserRegistrationRequest request);
}
