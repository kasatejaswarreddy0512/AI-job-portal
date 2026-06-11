package com.ktsr.mapper;

import com.ktsr.job.dto.ApiResponse;
import com.ktsr.model.User;

import java.util.List;
import java.util.stream.Collectors;


public class UserMapper {

    public static ApiResponse.UserResponse toDto(User user){
        ApiResponse.UserResponse dto = new ApiResponse.UserResponse();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFullName(user.getFullName());
        dto.setPhone(user.getPhone());
        dto.setProfileImage(user.getProfileImage());
        dto.setRole(user.getRole());
        dto.setStatus(user.getStatus());
        dto.setLastLogin(user.getLastLogin());
        dto.setCreatedAt(user.getCreatedAt());
        return  dto;
    }

    public static List<ApiResponse.UserResponse> toDtoList(List<User> users){
        return users.stream()
                .map(UserMapper::toDto).collect(Collectors.toList());
    }

}
