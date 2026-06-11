package com.ktsr.job.controller;

import com.ktsr.job.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping
    public ResponseEntity<ApiResponse> resume(){
        return ResponseEntity.ok(new ApiResponse("Service for managing candidate resumes, including resume builder, " +
                "multiple versions, and resume parsing", true));
    }

}
