package com.ktsr.job.service;


import com.ktsr.job.dto.JobSkillResponse;
import com.ktsr.job.model.JobSkill;
import com.ktsr.job.payload.JobSkillRequest;

import java.util.List;
import java.util.Set;

public interface JobSkillService {

    JobSkillResponse createSkill(JobSkillRequest request);

    List<JobSkillResponse>  getAllSkills();

    JobSkillResponse getSkillById(Long id);

    JobSkillResponse updateSkill(Long id,JobSkillRequest request);

    void deleteSkill(Long id);

    Set<JobSkill> getSkillByIds(Set<Long> ids);
}
