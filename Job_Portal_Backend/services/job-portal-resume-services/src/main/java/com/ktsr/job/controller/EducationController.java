package com.ktsr.job.controller;

import com.ktsr.job.dto.ApiResponse;
import com.ktsr.job.dto.EducationResponse;
import com.ktsr.job.model.Education;
import com.ktsr.job.payload.EducationRequest;
import com.ktsr.job.service.EducationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resume/{resumeId}/education")
@RequiredArgsConstructor
public class EducationController {

    private final EducationService educationService;

    @PostMapping
    public ResponseEntity<EducationResponse> addEducation(@PathVariable Long resumeId,
                                                          @RequestHeader("X-User-Id") Long candidateId,
                                                          @RequestBody@Valid EducationRequest educationRequest) {
        return new ResponseEntity<>(educationService.addEducation(resumeId, candidateId, educationRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EducationResponse>> getEducation(@PathVariable Long resumeId) {
        return ResponseEntity.ok(educationService.getEducations(resumeId));
    }

    @PutMapping("/{educationId}")
    public ResponseEntity<EducationResponse> updateEducation(@PathVariable Long educationId,
                                                             @PathVariable Long resumeId,
                                                             @RequestHeader("X-User-Id") Long candidateId,
                                                             @RequestBody @Valid EducationRequest educationRequest) {
        return ResponseEntity.ok(educationService.updateEducation(educationId, resumeId, candidateId, educationRequest));
    }

    @DeleteMapping("/{educationId}")
    public ResponseEntity<ApiResponse> deleteEducation(@PathVariable Long educationId,
                                                       @PathVariable Long resumeId,
                                                       @RequestHeader("X-User-Id") Long candidateId) {
        educationService.deleteEducation(resumeId, candidateId, educationId);
        return ResponseEntity.ok(new ApiResponse("Education Deleted Successfully...!", true));
    }
}
