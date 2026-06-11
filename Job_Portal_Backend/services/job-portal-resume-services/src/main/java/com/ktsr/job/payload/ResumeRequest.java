package com.ktsr.job.payload;

import com.ktsr.job.domain.ResumeTemplate;
import com.ktsr.job.domain.ResumeVisibility;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeRequest {

    @NotBlank(message = "Resume title is required")
    private String title;


    private ResumeTemplate template;

    private ResumeVisibility visibility;

    private Boolean isDefault = false;


}
