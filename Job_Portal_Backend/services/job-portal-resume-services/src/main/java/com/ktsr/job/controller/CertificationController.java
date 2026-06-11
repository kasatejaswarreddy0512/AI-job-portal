package com.ktsr.job.controller;

import com.ktsr.job.dto.AddCertificationRequest;
import com.ktsr.job.dto.ApiResponse;
import com.ktsr.job.dto.CertificationResponse;
import com.ktsr.job.service.CertificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resume/{resumeId}/certifications")
@RequiredArgsConstructor
public class CertificationController {

    private final CertificationService certificationService;

    @PostMapping
    public ResponseEntity<CertificationResponse> addCertification(@PathVariable Long resumeId,
                                                                  @RequestHeader("X-User-Id") Long  candidateId,
                                                                  @RequestBody @Valid AddCertificationRequest request){
        return new ResponseEntity<>(certificationService.addCertificate(resumeId,candidateId,request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CertificationResponse>> getAllCertification(@PathVariable Long resumeId){
        return ResponseEntity.ok(certificationService.getAllCertifications(resumeId));
    }

    @PutMapping("/{certificationId}")
    public ResponseEntity<CertificationResponse> updateCertification(@PathVariable Long certificationId,
                                                                     @PathVariable Long resumeId,
                                                                     @RequestHeader("X-User-Id") Long  candidateId,
                                                                     @RequestBody @Valid AddCertificationRequest request){
        return ResponseEntity.ok(certificationService.updateCertificate(certificationId,resumeId,candidateId,request));
    }

    @DeleteMapping("/{certificationId}")
    public ResponseEntity<ApiResponse> deleteCertification(@PathVariable Long certificationId,
                                                           @PathVariable Long resumeId,
                                                           @RequestHeader("X-User-Id") Long  candidateId){
        certificationService.deleteCertificate(certificationId,resumeId,candidateId);
        return ResponseEntity.ok(new ApiResponse("Certification Deleted Successfully..!", true) );
    }
}
