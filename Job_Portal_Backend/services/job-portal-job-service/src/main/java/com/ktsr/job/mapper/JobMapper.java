package com.ktsr.job.mapper;

import com.ktsr.job.dto.CompanyResponse;
import com.ktsr.job.dto.JobResponse;
import com.ktsr.job.dto.JobSkillResponse;
import com.ktsr.job.dto.JobTagResponse;
import com.ktsr.job.model.Job;
import com.ktsr.job.model.JobCategory;
import com.ktsr.job.model.JobLocation;
import com.ktsr.job.model.SalaryRange;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class JobMapper {

    public static JobResponse toResponse(Job job, CompanyResponse companyResponse){

        JobLocation loc=job.getLocation();
        SalaryRange sal= job.getSalaryRange();

        Set<JobSkillResponse> skills=job.getSkills()==null
                ? Collections.emptySet()
                : job.getSkills().stream().map(JobSkillMapper::toJobSkillResponse)
                  .collect(Collectors.toSet());

        Set<JobTagResponse> tags= job.getTags()==null
                ?Collections.emptySet()
                : job.getTags().stream().map(JobTagMapper::jobTagResponse)
                  .collect(Collectors.toSet());




        return  JobResponse.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .requirements(job.getRequirements())
                .responsibilities(job.getResponsibilities())
                .benefits(job.getBenefits())
                .company(companyResponse)
                .category(JobCategoryMapper.toResponse(job.getCategory(), false))
                .skills(skills)
                .tags(tags)
                //location
                .address(loc!=null ? loc.getAddress():null)
                .city(loc != null ? loc.getCity() :  null)
                .state(loc != null ? loc.getState() : null)
                .country(loc != null ? loc.getCountry() :  null)
                .zipCode( loc !=null ? loc.getZipCode() : null)
                //salary
                .minSalary(sal !=null ? sal.getMinSalary() :null)
                .maxSalary(sal != null  ? sal.getMaxSalary() : null)
                //classifications
                .jobType(job.getJobType())
                .workMode(job.getWorkMode())
                .experienceLevel(job.getExperienceLevel())
                .status(job.getStatus())
                //posting
                .openings(job.getOpenings())
                .applicationDeadLine(job.getApplicationDeadLine())
                .expiresAt(job.getExpiresAt())
                .active(job.getActive())
                //timeStamps
                .createdAt(job.getCreatedAt())
                .updatedAt(job.getUpdatedAt())
                .publishedAt(job.getPublishedAt())
                .closeAt(job.getCloseAt())
                .build();

    }
}
