package com.glory.userservice.service.impl;

import com.glory.userservice.dto.request.LoginRequest;
import com.glory.userservice.dto.request.UserRegistrationRequest;
import com.glory.userservice.dto.response.LoginResponse;
import com.glory.userservice.dto.response.UserResponse;
import com.glory.userservice.entity.User;
import com.glory.userservice.entity.UserRole;
import com.glory.userservice.mapper.UserMapper;
import com.glory.userservice.repository.UserRepository;
import com.glory.userservice.service.JwtService;
import com.glory.userservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

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

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials. User not found"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid credentials. User not found");
        }

        String token = jwtService.generateToken(String.valueOf(user.getId()),user.getEmail(), user.getRole().name());

        return new LoginResponse(token);
    }
}
