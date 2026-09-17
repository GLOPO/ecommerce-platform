package com.glory.userservice.mapper;

import com.glory.userservice.dto.request.UserRegistrationRequest;
import com.glory.userservice.dto.response.UserResponse;
import com.glory.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "passwordHash", ignore = true)
    User toEntity(UserRegistrationRequest request);

    UserResponse toResponse(User user);
}
