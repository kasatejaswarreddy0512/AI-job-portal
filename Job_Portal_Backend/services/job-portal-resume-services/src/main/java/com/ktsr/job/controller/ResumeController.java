package com.ktsr.job.controller;

import com.ktsr.job.dto.ApiResponse;
import com.ktsr.job.dto.PersonalInfoResponse;
import com.ktsr.job.dto.ResumeResponse;
import com.ktsr.job.model.PersonalInfo;
import com.ktsr.job.payload.ResumeRequest;
import com.ktsr.job.service.ResumeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resume")
@RequiredArgsConstructor
public class ResumeController {

    public final ResumeService resumeService;

    @PostMapping
    public ResponseEntity<ResumeResponse> createResume(@RequestHeader("X-User-Id") Long candidateId,
                                                       @RequestBody @Valid ResumeRequest resumeRequest) {
        return new ResponseEntity<>(resumeService.createResume(candidateId, resumeRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{resumeId}")
    public ResponseEntity<ResumeResponse> getResumeById(@PathVariable("resumeId") Long resumeId,
                                                        @RequestHeader("X-User-Id") Long candidateId) {
        return ResponseEntity.ok(resumeService.getResumeById(resumeId, candidateId));
    }

    @GetMapping("/my")
    public ResponseEntity<List<ResumeResponse>> getMyResumes(@RequestHeader("X-User-Id") Long candidateId) {
        return ResponseEntity.ok(resumeService.getMyResumes(candidateId));
    }

    @PutMapping("/{resumeId}/personal-info")
    public ResponseEntity<ResumeResponse> updatePersonalInfo(@PathVariable Long resumeId,
                                                             @RequestHeader("X-User-Id") Long candidateId,
                                                             @RequestBody @Valid PersonalInfoResponse info) {
        return ResponseEntity.ok(resumeService.updatePersonalInfo(resumeId,candidateId,info));
    }

    @PutMapping("/{resumeId}/summary")
    public ResponseEntity<ResumeResponse> updateSummary(@PathVariable Long resumeId,
                                                        @RequestHeader("X-User-Id") Long candidateId,
                                                        @RequestParam String summary) {
        return ResponseEntity.ok(resumeService.updateSummary(resumeId,candidateId,summary));
    }

    @PatchMapping("/{resumeId}/set-default")
    public ResponseEntity<ResumeResponse> setDefaultResume(@PathVariable Long resumeId,
                                                           @RequestHeader("X-User-Id") Long candidateId) {
        return ResponseEntity.ok(resumeService.setResumeDefault(resumeId,candidateId));
    }

    @DeleteMapping("/{resumeId}")
    public ResponseEntity<ApiResponse> deleteResume(@PathVariable Long resumeId,
                                                    @RequestHeader("X-User-Id") Long candidateId) {
        resumeService.deleteResume(resumeId, candidateId);
        return ResponseEntity.ok(new ApiResponse("Resume Deleted Successfully...!", true));
    }
}
