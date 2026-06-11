package com.ktsr.job.mapper;

import com.ktsr.job.dto.JobSkillResponse;
import com.ktsr.job.model.JobSkill;

public class JobSkillMapper {

    public static JobSkillResponse toJobSkillResponse(JobSkill jobSkill){

        return JobSkillResponse.builder()
                .id(jobSkill.getId())
                .name(jobSkill.getName())
                .slug(jobSkill.getSlug())
                .category(jobSkill.getCategory())
                .active(jobSkill.getActive())
                .build();
    }
}
