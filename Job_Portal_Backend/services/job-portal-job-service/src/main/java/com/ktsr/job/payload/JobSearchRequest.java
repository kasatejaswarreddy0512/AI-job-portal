package com.ktsr.job.payload;

import com.ktsr.job.domain.ExperienceLevel;
import com.ktsr.job.domain.JobStatus;
import com.ktsr.job.domain.JobType;
import com.ktsr.job.domain.WorkMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobSearchRequest {

    private String keyWord;
    private Long categoryId;

    private List<Long> skillIds;
    private List<Long> tagIds;

    private Long companyId;

    private String location;

    private BigDecimal minSalary;

    private BigDecimal maxSalary;

    private JobType jobType;

    private WorkMode workMode;

    private ExperienceLevel experienceLevel;

    private JobStatus jobStatus;

    private  Integer minOpenings;

    private Integer maxOpenings;
}
