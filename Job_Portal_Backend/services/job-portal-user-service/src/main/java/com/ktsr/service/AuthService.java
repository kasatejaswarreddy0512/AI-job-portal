package com.ktsr.service;

import com.ktsr.paylaod.AuthResponse;
import com.ktsr.paylaod.LoginRequest;
import com.ktsr.paylaod.SignupRequest;

public interface AuthService {

    AuthResponse signup(SignupRequest req);
    AuthResponse login(LoginRequest req);
}
