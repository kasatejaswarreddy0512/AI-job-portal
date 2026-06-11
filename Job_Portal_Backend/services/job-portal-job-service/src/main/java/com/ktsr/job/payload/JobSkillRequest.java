package com.ktsr.job.payload;

import com.ktsr.job.domain.SkillCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobSkillRequest {

    @NotBlank(message = "Skill name is mandatory..!")
    @Size(max = 100, message = "Name must not exceed 100 character")
    private String name;

    @NotNull(message = "Skill category is required")
    private SkillCategory category;

}
