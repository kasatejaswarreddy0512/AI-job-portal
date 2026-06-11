package com.ktsr.job.controller;

import com.ktsr.job.dto.ApiResponse;
import com.ktsr.job.dto.JobRequest;
import com.ktsr.job.dto.JobResponse;
import com.ktsr.job.payload.JobSearchRequest;
import com.ktsr.job.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping
    public ResponseEntity<JobResponse> createJob(@RequestHeader("X-User-Id") Long employeeId,
                                                 @RequestBody @Valid JobRequest request) {
        return new ResponseEntity<>(jobService.createJob(employeeId, request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    @GetMapping
    public ResponseEntity<List<JobResponse>> getJobs(@ModelAttribute JobSearchRequest request) {
        return ResponseEntity.ok(jobService.getJobs(request));
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<JobResponse>> getJobsByCompany(@PathVariable Long companyId) {
        return ResponseEntity.ok(jobService.getJobByCompany(companyId));
    }

    @GetMapping("/admin")
    public ResponseEntity<List<JobResponse>> getAllJobsAdmin() {
        return ResponseEntity.ok(jobService.getAllJobsAdmin());
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobResponse> updateJob(@PathVariable Long id,
                                                 @RequestHeader("X-User-Id") Long employeeId,
                                                 @RequestBody JobRequest request) {
        return new ResponseEntity<>(jobService.updateJob(id, employeeId, request), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/publish")
    public ResponseEntity<JobResponse> publishJob(@PathVariable Long id,
                                                  @RequestHeader("X-User-Id") Long employeeId) {
        return ResponseEntity.ok(jobService.publishJobs(id, employeeId));
    }

    @PatchMapping("/{id}/close")
    public ResponseEntity<JobResponse> closeJob(@PathVariable Long id,
                                                @RequestHeader("X-User-Id") Long employeeId) {
        return ResponseEntity.ok(jobService.closeJobs(id, employeeId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteJob(@PathVariable Long id,
                                                 @RequestHeader("X-User-Id") Long employeeId) {
        jobService.deleteJob(id, employeeId);
        return ResponseEntity.ok(new ApiResponse("Job Deleted Successfully...!", true));
    }

}
