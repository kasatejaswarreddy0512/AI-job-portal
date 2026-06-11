package com.ktsr.job.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AddAwardRequest {

    @NotBlank(message = "Award title is required")
    private String awardTitle;

    @NotBlank(message = "Issuing organization is required")
    private String issuingOrganization;

    @NotNull(message = "Award date is required")
    private LocalDate awardDate;

    private String description;


    private Integer displayOrder = 0;
}