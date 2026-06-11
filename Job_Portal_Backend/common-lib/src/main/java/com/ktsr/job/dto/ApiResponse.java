package com.ktsr.job.dto;

import com.ktsr.job.domain.UserRole;
import com.ktsr.job.domain.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private String message;
    private boolean status;


    @Data
    public static class UserResponse {
        private Long id;
        private String fullName;
        private String email;
        private String phone;
        private String profileImage;
        private UserRole role;
        private UserStatus status;
        private LocalDateTime createdAt;
        private LocalDateTime lastLogin;
    }
}
