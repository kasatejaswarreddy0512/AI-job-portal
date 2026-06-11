package com.ktsr.job.controller;

import com.ktsr.job.dto.ApiResponse;
import com.ktsr.job.dto.ProjectResponse;
import com.ktsr.job.payload.AddProjectRequest;
import com.ktsr.job.payload.ResumeRequest;
import com.ktsr.job.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resume/{resumeId}/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponse> addProject(@PathVariable Long resumeId,
                                                      @RequestHeader("X-User-Id") Long candidateId,
                                                      @RequestBody @Valid AddProjectRequest request) {
        return new  ResponseEntity<>(projectService.addProject(resumeId, candidateId, request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAllProjects(@PathVariable Long resumeId) {
        return ResponseEntity.ok(projectService.getAllProjects(resumeId));
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long projectId,
                                                         @PathVariable Long resumeId,
                                                         @RequestHeader("X-User-Id") Long candidateId,
                                                         @RequestBody @Valid AddProjectRequest request){
        return ResponseEntity.ok(projectService.updateProject(projectId, resumeId, candidateId, request));
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<ApiResponse> deleteProject(@PathVariable Long projectId,
                                                     @PathVariable Long resumeId,
                                                     @RequestHeader("X-User-Id") Long candidateId){
        projectService.deleteProject(projectId, resumeId, candidateId);
        return ResponseEntity.ok(new ApiResponse("Peoject Deleted Successfully...!", true));
    }

}
