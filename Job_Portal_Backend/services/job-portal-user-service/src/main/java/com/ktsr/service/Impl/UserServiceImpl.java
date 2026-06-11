package com.ktsr.service.Impl;

import com.ktsr.job.domain.UserStatus;
import com.ktsr.job.dto.ApiResponse;
import com.ktsr.mapper.UserMapper;
import com.ktsr.model.User;
import com.ktsr.paylaod.UpdateUserRequest;
import com.ktsr.repository.UserRepository;
import com.ktsr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) {
        User user=userRepository.findByEmail(email);
        if (user==null){
            throw new RuntimeException("User Not Found...!");
        }
        return user;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("User Not found...!"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public ApiResponse.UserResponse updateUser(String email, UpdateUserRequest request) {
        User user=getUserByEmail(email);

        if(request.getFullName()!=null){
            user.setFullName(request.getFullName());
        }
        if (request.getPhone()!=null){
            user.setPhone(user.getPhone());
        }
        if (request.getProfileImage()!=null){
            user.setProfileImage(user.getProfileImage());
        }

        return UserMapper.toDto(userRepository.save(user));
    }

    @Override
    public ApiResponse.UserResponse suspendUser(Long id) {
        User user=getUserById(id);
        user.setStatus(UserStatus.SUSPENDED);
        user.setSuspendedAt(LocalDateTime.now());
        return UserMapper.toDto(userRepository.save(user));
    }

    @Override
    public ApiResponse.UserResponse activateUser(Long id) {
        User user=getUserById(id);
        user.setStatus(UserStatus.ACTIVE);
        user.setSuspendedAt(null);
        return UserMapper.toDto(userRepository.save(user));

    }

    @Override
    public ApiResponse.UserResponse deleteUser(Long id) {
        User user=getUserById(id);
        user.setStatus(UserStatus.DELETED);
        user.setDeletedAt(LocalDateTime.now());
        return UserMapper.toDto(userRepository.save(user));
    }
}
