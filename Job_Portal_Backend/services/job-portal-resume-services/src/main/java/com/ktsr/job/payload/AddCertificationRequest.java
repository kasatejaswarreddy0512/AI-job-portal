package com.ktsr.job.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AddCertificationRequest {

    @NotBlank(message = "Certification name is required")
    private String certificationName;

    @NotBlank(message = "Issuing organization is required")
    private String issuingOrganization;

    @NotNull(message = "Issue date is required")
    private LocalDate issueDate;

    private LocalDate expirationDate;

    private Boolean doesNotExpire = false;

    private String description;

    private Integer displayOrder = 0;
}