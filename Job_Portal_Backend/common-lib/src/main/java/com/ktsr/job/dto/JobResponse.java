package com.ktsr.job.dto;

import com.ktsr.job.domain.ExperienceLevel;
import com.ktsr.job.domain.JobStatus;
import com.ktsr.job.domain.JobType;
import com.ktsr.job.domain.WorkMode;
//import com.ktsr.job.model.JobLocation;
//import com.ktsr.job.model.SalaryRange;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobResponse {

    private Long id;
    private String title;
    private String description;
    private String requirements;
    private String responsibilities;
    private String benefits;
    private CompanyResponse company;
    private Long companyId;


    private JobCategoryResponse category;
    private Set<JobSkillResponse> skills;
    private Set<JobTagResponse> tags;

    //Address
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;

    //salary
    private BigDecimal minSalary;
    private BigDecimal maxSalary;

    //Classifications
    private JobType jobType;
    private WorkMode workMode;
    private ExperienceLevel experienceLevel;
    private JobStatus status;

    //posting details
    private Integer openings;
    private LocalDate applicationDeadLine;
    private LocalDate expiresAt;
    private Boolean active;

    //Timestamps
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;
    private LocalDateTime closeAt;
}