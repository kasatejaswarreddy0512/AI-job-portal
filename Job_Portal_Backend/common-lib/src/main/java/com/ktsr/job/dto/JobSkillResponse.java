package com.ktsr.job.dto;

import com.ktsr.job.domain.SkillCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobSkillResponse {

    private Long id;
    private String name;
    private String slug;
    private SkillCategory category;
    private Boolean active;
}
