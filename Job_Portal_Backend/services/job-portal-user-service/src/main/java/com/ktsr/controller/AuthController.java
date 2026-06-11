package com.ktsr.controller;

import com.ktsr.paylaod.AuthResponse;
import com.ktsr.paylaod.LoginRequest;
import com.ktsr.paylaod.SignupRequest;
import com.ktsr.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private  final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody @Valid SignupRequest signupRequest){
        return new ResponseEntity<>(authService.signup(signupRequest), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }

}
