package com.ktsr.job.service;



import com.ktsr.job.dto.PersonalInfoResponse;
import com.ktsr.job.dto.ResumeResponse;
import com.ktsr.job.model.Resume;
import com.ktsr.job.payload.ResumeRequest;

import java.util.List;

public interface ResumeService {

    ResumeResponse createResume(Long candidateId, ResumeRequest request);

    ResumeResponse getResumeById(Long resumeId, Long candidateId);

    List<ResumeResponse> getMyResumes(Long candidateId);

    ResumeResponse updatePersonalInfo(Long resumeId, Long candidateId, PersonalInfoResponse request);

    ResumeResponse updateSummary(Long resumeId, Long candidateId, String summary);

    ResumeResponse setResumeDefault(Long resumeId, Long candidateId);

    void deleteResume(Long resumeId, Long candidateId);

    Resume getResumeEntity(Long resumeId);

}
