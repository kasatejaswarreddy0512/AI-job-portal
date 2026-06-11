package com.ktsr.job.dto;

import com.ktsr.job.domain.CompanySize;
import com.ktsr.job.domain.CompanyStatus;
import com.ktsr.job.domain.CompanyType;
import com.ktsr.job.domain.IndustryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyResponse {
    private Long id;
    private String name;
    private String slug;
    private String tagLine;
    private String description;
    private String logoUrl;
    private String coverImageUrl;
    private String website;
    private Integer foundedYear;
    private String email;
    private String phone;
    private CompanySize companySize;
    private CompanyType companyType;
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
