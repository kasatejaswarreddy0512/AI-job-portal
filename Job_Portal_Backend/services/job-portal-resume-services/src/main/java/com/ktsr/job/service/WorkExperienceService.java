package com.ktsr.job.service;

import com.ktsr.job.dto.WorkExperienceResponse;
import com.ktsr.job.model.WorkExperience;
import com.ktsr.job.payload.AddWorkExperience;

import java.util.List;

public interface WorkExperienceService {

    WorkExperienceResponse addWorkExperience(Long resumeId, Long candidateId, AddWorkExperience request);

    List<WorkExperienceResponse> getWorkExperiences(Long resumeId);

    WorkExperienceResponse updateWorkExperience(Long resumeId, Long candidateId,Long workExperienceId, AddWorkExperience request);

    void deleteWorkExperience(Long resumeId, Long workExperienceId, Long candidateId);

    WorkExperience getWorkExperience(Long workExperienceId);

}
