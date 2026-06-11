package com.ktsr.job.mapper;

import com.ktsr.job.dto.*;
import com.ktsr.job.model.*;

import java.util.List;

public class ResumeMapper {

    public static PersonalInfoResponse toPersonalInfoResponse(PersonalInfo personalInfo) {

        if (personalInfo == null) {return null;}

        return PersonalInfoResponse.builder()
                .firstName(personalInfo.getFirstName())
                .lastName(personalInfo.getLastName())
                .email(personalInfo.getEmail())
                .phoneNumber(personalInfo.getPhoneNumber())
                .headLine(personalInfo.getHeadLine())
                .city(personalInfo.getCity())
                .country(personalInfo.getCountry())
                .linkedinUrl(personalInfo.getLinkedinUrl())
                .githubUrl(personalInfo.getGithubUrl())
                .portfolioUrl(personalInfo.getPortfolioUrl())
                .websiteUrl(personalInfo.getWebsiteUrl())

                .build();
    }


    public static ResumeResponse toResumeResponse(Resume resume,
                                                  List<WorkExperienceResponse> workExperiences,
                                                  List<EducationResponse> educations,
                                                  List<ResumeSkillResponse> resumeSkills,
                                                  List<ProjectResponse> projects,
                                                  List<LanguageResponse> languages,
                                                  List<CertificationResponse> certifications,
                                                  List<AwardResponse> awards) {

        if (resume == null) {
            return null;
        }

        return ResumeResponse.builder()
                .id(resume.getId())
                .candidateId(resume.getCandidateId())
                .title(resume.getTitle())
                .template(resume.getTemplate())
                .visibility(resume.getVisibility())
                .isDefault(resume.getIsDefault())
                .personalInfo(ResumeMapper.toPersonalInfoResponse(resume.getPersonalInfo()))
                .summary(resume.getSummary())
                .completionScore(resume.getCompletionScore())
                .isActive(resume.getIsActive())
                .createdAt(resume.getCreatedAt())
                .updatedAt(resume.getUpdatedAt())
                .workExperiences(workExperiences)
                .educations(educations)
                .skills(resumeSkills)
                .certifications(certifications)
                .awards(awards)
                .projects(projects)
                .languages(languages)
                .build();
    }

    public static ResumeSkillResponse toResumeSkillResponse(ResumeSkill skill) {
        if (skill == null) {return null;}
        return ResumeSkillResponse.builder()
                .id(skill.getId())
                .skillName(skill.getSkillName())
                .proficiencyLevel(skill.getProficiencyLevel())
                .yearsOfExperience(skill.getYearsOfExperience())
                .displayOrder(skill.getDisplayOrder())
                .build();
    }

    public static EducationResponse toEducationResponse(Education education) {
        if (education == null) {return null;}

        return EducationResponse.builder()
                .id(education.getId())
                .instituteName(education.getInstituteName())
                .grade(education.getGrade())
                .fieldOfStudy(education.getFieldOfStudy())
                .startDate(education.getStartDate())
                .endDate(education.getEndDate())
                .isCurrentlyStudying(education.getIsCurrentlyStudying())
                .description(education.getDescription())
                .displayOrders(education.getDisplayOrders())
                .build();
    }

    public static ProjectResponse toProjectResponse(Project project) {
        if (project == null) {return null;}

        return ProjectResponse.builder()
                .id(project.getId())
                .title(project.getTitle())
                .description(project.getDescription())
                .technologies(project.getTechnologies())
                .projectUrl(project.getProjectUrl())
                .sourceCodeUrl(project.getSourceCodeUrl())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .isOngoing(project.getIsOngoing())
                .displayOrder(project.getDisplayOrder())
                .build();
    }

    public static LanguageResponse toLanguageResponse(Language language) {
        if (language == null) {return null;}

        return LanguageResponse.builder()
                .id(language.getId())
                .languageName(language.getLanguageName())
                .proficiency(language.getProficiency())
                .displayOrders(language.getDisplayOrders())
                .build();
    }

    public static CertificationResponse toCertificationResponse(Certification certification) {
        if (certification == null) {return null;}

        return CertificationResponse.builder()
                .id(certification.getId())
                .certificationName(certification.getCertificationName())
                .issuingOrganization(certification.getIssuingOrganization())
                .issueDate(certification.getIssueDate())
                .expirationDate(certification.getExpirationDate())
                .doesNotExpire(certification.getDoesNotExpire())
                .description(certification.getDescription())
                .displayOrder(certification.getDisplayOrder())
                .createdAt(certification.getCreatedAt())
                .updatedAt(certification.getUpdatedAt())
                .build();
    }

    public static AwardResponse toAwardResponse(Award award) {
        if (award == null) {return null;}

        return AwardResponse.builder()
                .id(award.getId())
                .awardTitle(award.getAwardTitle())
                .issuingOrganization(award.getIssuingOrganization())
                .awardDate(award.getAwardDate())
                .description(award.getDescription())
                .displayOrder(award.getDisplayOrder())
                .createdAt(award.getCreatedAt())
                .updatedAt(award.getUpdatedAt())
                .build();
    }
}
