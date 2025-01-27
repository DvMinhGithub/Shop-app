package com.project.shopapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.project.shopapp.dto.request.UserCreateRequest;
import com.project.shopapp.dto.request.UserLoginRequest;
import com.project.shopapp.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toUser(UserCreateRequest request);

    UserLoginRequest toUser(UserLoginRequest request);
}
