package com.ktsr.controller;

import com.ktsr.job.dto.ApiResponse;
import com.ktsr.mapper.UserMapper;
import com.ktsr.model.User;
import com.ktsr.paylaod.UpdateUserRequest;
import com.ktsr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private  final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse.UserResponse> getProfile(@RequestHeader("X-User-Email") String email){
        User user=userService.getUserByEmail(email);
        return ResponseEntity.ok(UserMapper.toDto(user));
    }
    @PutMapping("/udpate-profile")
    public ResponseEntity<ApiResponse.UserResponse> updateProfile(@RequestHeader("X-User-Email") String email,
                                                                  @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok((userService.updateUser(email,request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse.UserResponse> getUserById(@PathVariable Long id){
        User user=userService.getUserById(id);
        return ResponseEntity.ok(UserMapper.toDto(user));
    }
    @GetMapping
    public ResponseEntity<List<ApiResponse.UserResponse>> getAllUser(){
        List<User> user=userService.getAllUsers();
        return ResponseEntity.ok(UserMapper.toDtoList(user));
    }

    @PatchMapping("/suspend/{userId}")
    public ResponseEntity<ApiResponse.UserResponse> suspendUser(@PathVariable Long userId){
        return ResponseEntity.ok(userService.suspendUser(userId));
    }

    @PatchMapping("/active/{userId}")
    public ResponseEntity<ApiResponse.UserResponse> activeUser(@PathVariable Long userId){
        return ResponseEntity.ok(userService.activateUser(userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse.UserResponse> deleteUser(@PathVariable Long userId){
        return ResponseEntity.ok(userService.deleteUser(userId));
    }
}
