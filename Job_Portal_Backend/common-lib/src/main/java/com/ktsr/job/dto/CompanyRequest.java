package com.ktsr.job.dto;

import com.ktsr.job.domain.CompanySize;
import com.ktsr.job.domain.CompanyStatus;
import com.ktsr.job.domain.CompanyType;
import com.ktsr.job.domain.IndustryType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyRequest {

    @NotBlank(message = "Company name is required")
    private String name;

    private String slug;

    private String tagLine;
    private String description;
    private String logoUrl;
    private String coverImageUrl;

    @Pattern(regexp = "^(https?://).*", message = "Website must be a valid url")
    private String website;

    @Min(value = 1800, message = "Founded year seems too old")
    @Max(value = 2100, message = "Founded year is invalid")
    private Integer foundedYear;

    @Email(message = "Company email must be valid")
    private String email;

    private String phone;

    @NotNull(message = "Company size is required")
    private CompanySize companySize;

    @NotNull(message = "Company type is required")
    private CompanyType companyType;

    @NotNull(message = "Industry type is required")
    private IndustryType industryType;

    private CompanyStatus companyStatus;

    private Boolean isVerified;

    private String registerNumber;
    private Long ownerId;
    private List<SocialLinkResponse> socialLinks;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}