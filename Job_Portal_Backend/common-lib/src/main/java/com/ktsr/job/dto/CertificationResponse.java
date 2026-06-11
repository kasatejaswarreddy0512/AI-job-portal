package com.ktsr.job.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CertificationResponse {

    private Long id;

    private String certificationName;

    private String issuingOrganization;

    private LocalDate issueDate;

    private LocalDate expirationDate;

    private Boolean doesNotExpire;

    private String description;

    private Integer displayOrder;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}