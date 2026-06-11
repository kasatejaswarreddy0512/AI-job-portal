package com.ktsr.job.service;


import com.ktsr.job.dto.EducationResponse;
import com.ktsr.job.model.Education;
import com.ktsr.job.payload.EducationRequest;

import java.util.List;

public interface EducationService {

    EducationResponse addEducation(Long resumeId, Long candidateId, EducationRequest request);

    List<EducationResponse> getEducations(Long resumeId);

    EducationResponse updateEducation(Long educationId,Long resumeId, Long candidateId, EducationRequest request);

    void deleteEducation(Long educationId,  Long resumeId, Long candidateId);

//    Education getEducationEntity(Long educationId);
}
