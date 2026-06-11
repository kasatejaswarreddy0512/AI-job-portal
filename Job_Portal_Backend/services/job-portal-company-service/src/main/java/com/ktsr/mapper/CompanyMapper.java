package com.ktsr.mapper;


import com.ktsr.job.dto.CompanyResponse;
import com.ktsr.job.dto.SocialLinkResponse;
import com.ktsr.model.Company;
import com.ktsr.model.SocialLinks;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyMapper {

    public static CompanyResponse toResponse(Company company){

        List<SocialLinkResponse> socialLinks=company.getSocialLinks()==null
                ? Collections.emptyList() :
                company.getSocialLinks().stream()
                                            .map(CompanyMapper::toSocialLinkResponse)
                                            .collect(Collectors.toList());

        return  CompanyResponse.builder()
                .id(company.getId())
                .name(company.getName())
                .slug(company.getSlug())
                .tagLine(company.getTagLine())
                .description(company.getDescription())
                .logoUrl(company.getLogoUrl())
                .coverImageUrl(company.getCoverImageUrl())
                .website(company.getWebsite())
                .email(company.getEmail())
                .phone(company.getPhone())
                .foundedYear(company.getFoundedYear())
                .companySize(company.getCompanySize())
                .companyType(company.getCompanyType())
                .industryType(company.getIndustryType())
                .registerNumber(company.getRegisterNumber())
                .companyStatus(company.getCompanyStatus())
                .isVerified(company.getIsVerified())
                .active(company.getActive())
                .ownerId(company.getOwnerId())
                .socialLinks(socialLinks)

                .createdAt(company.getCreatedAt())
                .updatedAt(company.getUpdatedAt())
                .build();

    }

    public static SocialLinkResponse toSocialLinkResponse(SocialLinks socialLinks) {
        return SocialLinkResponse.builder()
                .socialPlatform(socialLinks.getSocialPlatform())
                .url(socialLinks.getUrl())
                .build();
    }
}
