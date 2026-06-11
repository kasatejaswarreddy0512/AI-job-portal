package com.ktsr.job.service.impl;

import com.ktsr.job.dto.ProjectResponse;
import com.ktsr.job.mapper.ResumeMapper;
import com.ktsr.job.model.Project;
import com.ktsr.job.model.Resume;
import com.ktsr.job.payload.AddProjectRequest;
import com.ktsr.job.repository.ProjectRepository;
import com.ktsr.job.service.ProjectService;
import com.ktsr.job.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ResumeService resumeService;

    @Override
    public ProjectResponse addProject(Long resumeId, Long candidateId, AddProjectRequest request) {
        Resume resume= resumeService.getResumeEntity(resumeId);
        assertOwner(resume, candidateId);

        Project project=Project.builder()
                .resume(resume)
                .title(request.getTitle())
                .description(request.getDescription())
                .technologies(request.getTechnologies() !=null ? request.getTechnologies():List.of())
                .projectUrl(request.getProjectUrl())
                .sourceCodeUrl(request.getSourceCodeUrl())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .isOngoing(Boolean.TRUE.equals(request.getIsOngoing()))
                .displayOrder(request.getDisplayOrder()!=null ? request.getDisplayOrder():0)
                .build();

        Project saved=projectRepository.save(project);
        return ResumeMapper.toProjectResponse(saved);
    }

    @Override
    public List<ProjectResponse> getAllProjects(Long resumeId) {
        return projectRepository.findByResumeIdOrderByDisplayOrderAsc(resumeId)
                .stream().map(ResumeMapper::toProjectResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectResponse updateProject(Long projectId, Long resumeId, Long candidateId, AddProjectRequest request) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(()-> new RuntimeException("project not found"));
        assertOwner(project.getResume(), candidateId);

        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        if (request.getTechnologies()!=null) project.setTechnologies(request.getTechnologies());
        project.setProjectUrl(request.getProjectUrl());
        project.setSourceCodeUrl(request.getSourceCodeUrl());
        project.setStartDate(request.getStartDate());
        project.setEndDate(request.getEndDate());
        project.setIsOngoing(Boolean.TRUE.equals(request.getIsOngoing()));
        if (request.getDisplayOrder()!=null) project.setDisplayOrder(request.getDisplayOrder());

        Project updated=projectRepository.save(project);
        return ResumeMapper.toProjectResponse(updated);
    }

    @Override
    public void deleteProject(Long resumeId, Long projectId, Long candidateId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(()-> new RuntimeException("project not found"));
        assertOwner(project.getResume(), candidateId);
        projectRepository.delete(project);
    }

    private void assertOwner(Resume resume, Long candidateId) {
        if (!resume.getCandidateId().equals(candidateId)) {
            throw new IllegalArgumentException("candidate id does not match");
        }
    }
}
