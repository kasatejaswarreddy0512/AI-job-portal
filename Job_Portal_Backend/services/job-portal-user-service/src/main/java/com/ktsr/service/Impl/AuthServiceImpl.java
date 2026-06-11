package com.ktsr.service.Impl;

import com.ktsr.job.domain.UserRole;
import com.ktsr.job.domain.UserStatus;
import com.ktsr.mapper.UserMapper;
import com.ktsr.model.User;
import com.ktsr.paylaod.AuthResponse;
import com.ktsr.paylaod.LoginRequest;
import com.ktsr.paylaod.SignupRequest;
import com.ktsr.repository.UserRepository;
import com.ktsr.security.CustomUserDetailsService;
import com.ktsr.security.JwtProvider;
import com.ktsr.service.AuthService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public AuthResponse signup(SignupRequest req) {
        if(userRepository.existsByEmail(req.getEmail())){
            throw new RuntimeException("Email already exists....!");
        }
        if(req.getRole()== UserRole.ROLE_ADMIN){
            throw  new RuntimeException("Cannot self register as role admin");
        }
        User user=User.builder()
                .fullName(req.getFullName())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .role(req.getRole())
                .phone(req.getPhone())
                .profileImage(req.getProfileImage())
                .lastLogin(LocalDateTime.now())
                .status(UserStatus.ACTIVE)
                .build();
        User savedUser= userRepository.save(user);

        Authentication authentication= new UsernamePasswordAuthenticationToken(
                user.getEmail(),user.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt =jwtProvider.generateToken(authentication,savedUser.getId());

        AuthResponse response= new AuthResponse();
        response.setTitle("Welcome  "+savedUser.getFullName());
        response.setMessage("Registered  Successfully....!");
        response.setJwt(jwt);
        response.setUser(UserMapper.toDto(savedUser));
        return response;
    }

    @Override
    public AuthResponse login(LoginRequest req) {
        Authentication authentication= authenticate(
                req.getEmail(),req.getPassword()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user= userRepository.findByEmail(req.getEmail());

        String jwt = jwtProvider.generateToken(authentication,user.getId());

        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        AuthResponse response= new AuthResponse();
        response.setTitle("Welcome Back -- "+user.getFullName());
        response.setMessage("Login  Successfully....!");
        response.setJwt(jwt);
        response.setUser(UserMapper.toDto(user));
        return response;
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails= customUserDetailsService.loadUserByUsername(email);
        if(userDetails==null){
            throw  new RuntimeException("User not found with email "+ email);
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw  new RuntimeException("Invalid Password");
        }
        return  new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
