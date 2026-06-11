package com.ktsr.paylaod;

import com.ktsr.job.dto.ApiResponse;
import lombok.Data;

@Data
public class AuthResponse {

    private String jwt;
    private  String title;
    private String message;
    private ApiResponse.UserResponse user;

}
