package com.ktsr.job.payload;


import com.ktsr.job.domain.ProficiencyLevel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeSkillRequest {

    @NotBlank(message = "Skill name is required")
    private String skillName;

    @NotNull(message = "Proficiency level is required")
    private ProficiencyLevel proficiencyLevel;

    @Min(value = 0, message = "Years of experience must not be negative")
    private Integer yearsOfExperience;
    private Integer displayOrder;
}
