package com.ktsr.job.service.impl;

import com.ktsr.job.dto.WorkExperienceResponse;
import com.ktsr.job.mapper.WorkExperienceMapper;
import com.ktsr.job.model.Resume;
import com.ktsr.job.model.WorkExperience;
import com.ktsr.job.payload.AddWorkExperience;
import com.ktsr.job.repository.WorkExperienceRepository;
import com.ktsr.job.service.ResumeService;
import com.ktsr.job.service.WorkExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkExperienceServiceImpl implements WorkExperienceService {

    private final WorkExperienceRepository workExperienceRepository;
    private final ResumeService resumeService;

    @Override
    public WorkExperienceResponse addWorkExperience(Long resumeId, Long candidateId, AddWorkExperience request) {
        Resume resume = resumeService.getResumeEntity(resumeId);
        assertOwner(resume, candidateId);

        WorkExperience workExperience = WorkExperience.builder()
                .resume(resume)
                .companyName(request.getCompanyName())
                .companyLogoUrl(request.getCompanyLogoUrl())
                .jobTitle(request.getJobTitle())
                .employmentType(request.getEmploymentType())
                .location(request.getLocation())
                .startDate(request.getStratDate())
                .endDate(request.getEndDate())
                .isCurrentJob(Boolean.TRUE.equals(request.getIsCurrentJob()))
                .description(request.getDescription())
                .technologies(request.getTechnologies() != null ? request.getTechnologies() : List.of())
                .displayOrder(request.getDisplayOrder() != null ? request.getDisplayOrder() : 0)
                .build();

        WorkExperience saved = workExperienceRepository.save(workExperience);

        return WorkExperienceMapper.toWorkExperienceResponse(saved);
    }


    @Override
    public List<WorkExperienceResponse> getWorkExperiences(Long resumeId) {
        return workExperienceRepository.findByResumeIdOrderByDisplayOrderAsc(resumeId)
                .stream().map(WorkExperienceMapper::toWorkExperienceResponse)
                .collect(Collectors.toList());
    }

    @Override
    public WorkExperienceResponse updateWorkExperience(Long resumeId, Long candidateId, Long workExperienceId, AddWorkExperience request) {
        WorkExperience experience = getWorkExperience(workExperienceId);
        assertOwner(experience.getResume(), candidateId);

        experience.setCompanyName(request.getCompanyName());
        experience.setCompanyLogoUrl(request.getCompanyLogoUrl());
        experience.setJobTitle(request.getJobTitle());
        experience.setEmploymentType(request.getEmploymentType());
        experience.setLocation(request.getLocation());
        experience.setStartDate(request.getStratDate());
        experience.setEndDate(request.getEndDate());
        experience.setIsCurrentJob(request.getIsCurrentJob());
        if (request.getTechnologies() != null) experience.setTechnologies(request.getTechnologies());
        if (request.getDisplayOrder() != null) experience.setDisplayOrder(request.getDisplayOrder());

        WorkExperience updated= workExperienceRepository.save(experience);
        return WorkExperienceMapper.toWorkExperienceResponse(updated);
    }

    @Override
    public void deleteWorkExperience(Long resumeId, Long workExperienceId, Long candidateId) {
        WorkExperience workExperience = getWorkExperience(workExperienceId);
        assertOwner(workExperience.getResume(), candidateId);
        workExperienceRepository.delete(workExperience);
    }

    @Override
    public WorkExperience getWorkExperience(Long workExperienceId) {
        return workExperienceRepository.findById(workExperienceId)
                .orElseThrow(() -> new RuntimeException("Resume Not Found..!"));
    }

    private void assertOwner(Resume resume, Long candidateId) {
        if (!resume.getCandidateId().equals(candidateId)) {
            throw new RuntimeException("Resume candidate id not match");
        }
    }

}
