package com.ktsr.job.controller;

import com.ktsr.job.dto.ApiResponse;
import com.ktsr.job.service.ResumeService;
import com.ktsr.job.service.impl.ResumeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final ResumeServiceImpl resumeService;

    @RequestMapping
    public ResponseEntity<ApiResponse> resume(){
        return ResponseEntity.ok(new ApiResponse("Service for managing candidate resumes, including resume builder, " +
                "multiple versions, and resume parsing", true));
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        resumeService.test();
        return ResponseEntity.ok("Hello redis Test");
    }

}
