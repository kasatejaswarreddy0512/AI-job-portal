package com.ktsr.job.mapper;

import com.ktsr.job.dto.WorkExperienceResponse;
import com.ktsr.job.model.WorkExperience;

import java.util.ArrayList;

public class WorkExperienceMapper {

    public static WorkExperienceResponse toWorkExperienceResponse(WorkExperience workExperience) {

        if (workExperience == null) {return null;}

        return WorkExperienceResponse.builder()
                .id(workExperience.getId())
                .companyName(workExperience.getCompanyName())
                .companyLogoUrl(workExperience.getCompanyLogoUrl())
                .jobTitle(workExperience.getJobTitle())
                .employmentType(workExperience.getEmploymentType())
                .location(workExperience.getLocation())
                .startDate(workExperience.getStartDate())
                .endDate(workExperience.getEndDate())
                .isCurrentJob(workExperience.getIsCurrentJob())
                .description(workExperience.getDescription())
                .technologies(
                        workExperience.getTechnologies() == null
                                ? new ArrayList<>()
                                : new ArrayList<>(workExperience.getTechnologies())
                )
                .displayOrder(workExperience.getDisplayOrder())
                .build();
    }
}
