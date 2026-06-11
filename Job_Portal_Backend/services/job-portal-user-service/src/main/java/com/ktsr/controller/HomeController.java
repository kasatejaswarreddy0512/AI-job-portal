package com.ktsr.controller;

import com.ktsr.job.domain.UserRole;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String homeController(){
        return "Job Portal User Service--------"+ UserRole.ROLE_ADMIN;
    }
}
