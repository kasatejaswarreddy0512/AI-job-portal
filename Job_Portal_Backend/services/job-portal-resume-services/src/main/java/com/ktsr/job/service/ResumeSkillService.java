package com.ktsr.job.service;

import com.ktsr.job.dto.ResumeSkillResponse;
import com.ktsr.job.model.ResumeSkill;
import com.ktsr.job.payload.ResumeSkillRequest;

import java.util.List;

public interface ResumeSkillService {

    ResumeSkillResponse addSkill(Long resumeId, Long candidateId, ResumeSkillRequest request);

    List<ResumeSkillResponse> getSkills(Long resumeId);

    ResumeSkillResponse updateSkill(Long skillId,Long resumeId, Long candidateId,ResumeSkillRequest request);

    void deleteSkill(Long resumeId, Long skillId,Long candidateId);

}
