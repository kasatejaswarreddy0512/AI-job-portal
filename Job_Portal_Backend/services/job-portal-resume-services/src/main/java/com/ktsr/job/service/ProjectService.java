package com.ktsr.job.service;

import com.ktsr.job.dto.ProjectResponse;
import com.ktsr.job.payload.AddProjectRequest;

import java.util.List;

public interface ProjectService {

    ProjectResponse addProject(Long resumeId, Long candidateId, AddProjectRequest project);

    List<ProjectResponse> getAllProjects(Long resumeId);

    ProjectResponse updateProject(Long projectId, Long resumeId, Long candidateId, AddProjectRequest project);

    void deleteProject(Long resumeId, Long projectId, Long candidateId);

}
