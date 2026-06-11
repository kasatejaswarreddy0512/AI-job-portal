package com.ktsr.job.controller;

import com.ktsr.job.domain.UserRole;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String home(){
        return "Service for managing job posting, search, and filtering" + UserRole.ROLE_EMPLOYER;
    }
}
