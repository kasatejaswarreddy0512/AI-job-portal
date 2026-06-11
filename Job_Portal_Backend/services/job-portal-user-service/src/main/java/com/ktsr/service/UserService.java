package com.ktsr.service;

import com.ktsr.job.dto.ApiResponse;
import com.ktsr.model.User;
import com.ktsr.paylaod.UpdateUserRequest;

import java.util.List;

public interface UserService {
    User getUserByEmail(String email);
    User getUserById(Long id);
    List<User> getAllUsers();

    ApiResponse.UserResponse updateUser(String email, UpdateUserRequest request);

    // Admin Action

    ApiResponse.UserResponse suspendUser(Long id);
    ApiResponse.UserResponse activateUser(Long id);
    ApiResponse.UserResponse deleteUser(Long id);
}
