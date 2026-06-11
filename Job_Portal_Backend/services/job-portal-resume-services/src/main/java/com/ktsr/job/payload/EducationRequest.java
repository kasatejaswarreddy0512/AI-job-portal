package com.ktsr.job.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationRequest {

    @NotBlank(message = "Institute Name is required")
    private String instituteName;

    @NotBlank(message = "Degree is required")
    private String degree;

    private String fieldOfStudy;
    private String grade;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isCurrentlyStudying;
    private String description;
    private Integer displayOrders;
}
