package com.ktsr.job.controller;

import com.ktsr.job.dto.ApiResponse;
import com.ktsr.job.dto.WorkExperienceResponse;
import com.ktsr.job.payload.AddWorkExperience;
import com.ktsr.job.service.WorkExperienceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resume/{resumeId}/work-experience")
@RequiredArgsConstructor
public class WorkExperienceController {

    private final WorkExperienceService workExperienceService;

    @PostMapping
    public ResponseEntity<WorkExperienceResponse> getWorkExperience(@PathVariable Long resumeId,
                                                                    @RequestHeader("X-User-Id") Long candidateId,
                                                                    @RequestBody @Valid AddWorkExperience experience) {
        return new ResponseEntity<>(workExperienceService.addWorkExperience(resumeId, candidateId, experience), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<WorkExperienceResponse>> getWorkExperiences(@PathVariable Long resumeId) {
        return ResponseEntity.ok(workExperienceService.getWorkExperiences(resumeId));
    }

    @PutMapping("/{experienceId}")
    public ResponseEntity<WorkExperienceResponse> updateWorkExperience(@PathVariable Long resumeId,
                                                                       @PathVariable Long experienceId,
                                                                       @RequestHeader("X-User-Id") Long candidateId,
                                                                       @RequestBody @Valid AddWorkExperience experience) {
        return ResponseEntity.ok(workExperienceService.updateWorkExperience(resumeId, experienceId, candidateId, experience));
    }

    @DeleteMapping("/{experienceId}")
    public ResponseEntity<ApiResponse> deleteWorkExperience(@PathVariable Long resumeId,
                                                            @PathVariable Long experienceId,
                                                            @RequestHeader("X-User-Id") Long candidateId) {
        workExperienceService.deleteWorkExperience(resumeId, experienceId, candidateId);
        return ResponseEntity.ok(new ApiResponse("Work Experience Deleted Successfully...!", true));
    }

}
