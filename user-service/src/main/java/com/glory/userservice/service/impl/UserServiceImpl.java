package com.glory.userservice.service.impl;

import com.glory.userservice.dto.request.UserRegistrationRequest;
import com.glory.userservice.dto.response.UserResponse;
import com.glory.userservice.entity.User;
import com.glory.userservice.entity.UserRole;
import com.glory.userservice.mapper.UserMapper;
import com.glory.userservice.repository.UserRepository;
import com.glory.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse register(UserRegistrationRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new IllegalArgumentException("User already exists");
        }

        User user = userMapper.toEntity(request);
        user.setRole(UserRole.USER);
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        User saved = userRepository.save(user);

        return userMapper.toResponse(saved);
    }
}
