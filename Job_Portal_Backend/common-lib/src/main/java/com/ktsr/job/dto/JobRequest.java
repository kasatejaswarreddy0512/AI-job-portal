package com.ktsr.job.dto;

import com.ktsr.job.domain.ExperienceLevel;
import com.ktsr.job.domain.JobStatus;
import com.ktsr.job.domain.JobType;
import com.ktsr.job.domain.WorkMode;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobRequest {

    @NotBlank(message = "Job title is required")
    private String title;
    @NotBlank(message = "Job description is required")
    private String description;

    private String requirements;
    private String responsibilities;
    private String benefits;

    private CompanySummaryResponse company;
    private Long companyId;

    @NotNull(message = "Category is required")
    private Long  categoryId;

    private Set<Long> skillIds;
    private Set<Long> tagIds;

    //Address
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;

    //salary
    @DecimalMin(value = "0.0", inclusive = true, message = "Min salary must not be negative")
    private BigDecimal minSalary;
    @DecimalMin(value = "0.0" , inclusive = true, message = "Max Salary must not be negative")
    private BigDecimal maxSalary;

    //Classifications
    @NotNull(message = "Job type is required")
    private JobType jobType;
    @NotNull(message = "Work mode is required")
    private WorkMode workMode;
    @NotNull(message = "Experience level is required")
    private ExperienceLevel experienceLevel;
    private JobStatus status;

    //posting details
    @Min(value = 1, message = "Openings must be at least 1")
    private Integer openings=1;
    private LocalDate applicationDeadLine;
    private LocalDate expiresAt;
    private Boolean active;
//
//    //Timestamps
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
//    private LocalDateTime publishedAt;
//    private LocalDateTime closeAt;


}
