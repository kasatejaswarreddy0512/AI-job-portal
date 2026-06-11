package com.ktsr.job.mapper;

import com.ktsr.job.dto.JobTagResponse;
import com.ktsr.job.model.JobTag;

public class JobTagMapper {


    public static JobTagResponse jobTagResponse(JobTag jobTag){
        return JobTagResponse.builder()
                .id(jobTag.getId())
                .name(jobTag.getName())
                .slug(jobTag.getSlug())
                .build();
    }

}
