package com.ktsr.model;

import com.ktsr.job.domain.UserRole;
import com.ktsr.job.domain.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private  String password;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    private String profileImage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role=UserRole.ROLE_JOB_SEEKER;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status=UserStatus.ACTIVE;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime updatedAt;


    private LocalDateTime lastLogin;

    private LocalDateTime suspendedAt;

    private LocalDateTime deletedAt;


}
