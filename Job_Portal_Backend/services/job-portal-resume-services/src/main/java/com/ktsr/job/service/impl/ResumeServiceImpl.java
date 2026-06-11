package com.ktsr.job.service.impl;

import com.ktsr.job.dto.*;
import com.ktsr.job.mapper.ResumeMapper;
import com.ktsr.job.mapper.WorkExperienceMapper;
import com.ktsr.job.model.PersonalInfo;
import com.ktsr.job.model.Resume;
import com.ktsr.job.model.WorkExperience;
import com.ktsr.job.payload.ResumeRequest;
import com.ktsr.job.repository.*;
import com.ktsr.job.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final WorkExperienceRepository workExperienceRepository;
    private final EducationRepository educationRepository;
    private final ResumeSkillRepository resumeSkillRepository;
    private final ProjectRepository projectRepository;
    private final LanguageRepository languageRepository;
    private final CertificationRepository certificationRepository;
    private final AwardRepository awardRepository;

    @Override
    public ResumeResponse createResume(Long candidateId, ResumeRequest request) {

        //if create new resume with as default resume clear older one any exists
        if (Boolean.TRUE.equals(request.getIsDefault())) {
            resumeRepository.findByCandidateIdAndIsDefaultTrue(candidateId)
                    .ifPresent(existingResume -> {
                        existingResume.setIsDefault(false);
                        resumeRepository.save(existingResume);
                    });
        }

        Resume resume = Resume.builder()
                .title(request.getTitle())
                .candidateId(candidateId)
                .template(request.getTemplate())
                .visibility(request.getVisibility())
                .isDefault(Boolean.TRUE.equals(request.getIsDefault()))
                .isActive(true)
                .build();

        Resume saved = resumeRepository.save(resume);

        return buildFullResponse(saved);
    }

    @Override
    public ResumeResponse getResumeById(Long resumeId, Long candidateId) {
        Resume resume = getResumeEntity(resumeId);
        assertOwner(resume, candidateId);
        return buildFullResponse(resume);
    }


    @Override
    public List<ResumeResponse> getMyResumes(Long candidateId) {
        return resumeRepository.findByCandidateIdAndIsActiveTrue(candidateId)
                .stream().map(this::buildFullResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ResumeResponse updatePersonalInfo(Long resumeId, Long candidateId, PersonalInfoResponse request) {
        Resume resume = getResumeEntity(resumeId);
        assertOwner(resume, candidateId);

        PersonalInfo info = resume.getPersonalInfo();
        if (info == null) {
            info = new PersonalInfo();
        }

        if (request.getFirstName() != null) info.setFirstName(request.getFirstName());
        if (request.getLastName() != null) info.setLastName(request.getLastName());
        if (request.getEmail() != null) info.setEmail(request.getEmail());
        if (request.getPhoneNumber() != null) info.setPhoneNumber(request.getPhoneNumber());
        if (request.getHeadLine() != null) info.setHeadLine(request.getHeadLine());
        if (request.getCity() != null) info.setCity(request.getCity());
        if (request.getCountry() != null) info.setCountry(request.getCountry());
        if (request.getLinkedinUrl() != null) info.setLinkedinUrl(request.getLinkedinUrl());
        if (request.getGithubUrl() != null) info.setGithubUrl(request.getGithubUrl());
        if (request.getWebsiteUrl() != null) info.setWebsiteUrl(request.getWebsiteUrl());
        if (request.getPortfolioUrl() != null) info.setPortfolioUrl(request.getPortfolioUrl());

        resume.setPersonalInfo(info);
        Resume updated = resumeRepository.save(resume);

        return buildFullResponse(updated);
    }

    @Override
    public ResumeResponse updateSummary(Long resumeId, Long candidateId, String summary) {
        Resume resume = getResumeEntity(resumeId);
        assertOwner(resume, candidateId);

        resume.setSummary(summary);
        Resume updated = resumeRepository.save(resume);

        return buildFullResponse(updated);
    }

    @Override
    public ResumeResponse setResumeDefault(Long resumeId, Long candidateId) {
        Resume resume = getResumeEntity(resumeId);
        assertOwner(resume, candidateId);

        resumeRepository.findByCandidateIdAndIsDefaultTrue(candidateId)
                .ifPresent(existingResume -> {
                    existingResume.setIsDefault(false);
                    resumeRepository.save(existingResume);
                });

        resume.setIsDefault(true);
        Resume updated = resumeRepository.save(resume);

        return buildFullResponse(updated);
    }

    @Override
    public void deleteResume(Long resumeId, Long candidateId) {
        Resume resume = getResumeEntity(resumeId);
        assertOwner(resume, candidateId);
        resume.setIsActive(false);
        resume.setIsDefault(false);
        resumeRepository.save(resume);

    }

    @Override
    public Resume getResumeEntity(Long resumeId) {
        return resumeRepository.findById(resumeId).orElseThrow(() -> new RuntimeException("resume not found" + resumeId));

    }

    private ResumeResponse buildFullResponse(Resume resume) {

        Long resumeId = resume.getId();

        List<WorkExperienceResponse> workExperienceResponses=
                workExperienceRepository.findByResumeIdOrderByDisplayOrderAsc(resumeId)
                        .stream()
                        .map(WorkExperienceMapper::toWorkExperienceResponse)
                        .toList();

        List<EducationResponse> educationResponses=
                educationRepository.findByResumeIdOrderByDisplayOrdersAsc(resumeId)
                        .stream()
                        .map(ResumeMapper::toEducationResponse)
                        .toList();
        List<ResumeSkillResponse> resumeSkillResponses=
                resumeSkillRepository.findByResumeIdOrderByDisplayOrderAsc(resumeId)
                        .stream().map(ResumeMapper::toResumeSkillResponse)
                        .toList();

        List<ProjectResponse> projectResponses=
                projectRepository.findByResumeIdOrderByDisplayOrderAsc(resumeId)
                        .stream().map(ResumeMapper::toProjectResponse)
                        .toList();

        List<LanguageResponse> languageResponses=
                languageRepository.findByResumeIdOrderByDisplayOrdersAsc(resumeId)
                        .stream().map(ResumeMapper::toLanguageResponse)
                        .toList();

        List<CertificationResponse> certificationResponses=
                certificationRepository.findByResumeIdOrderByDisplayOrderAsc(resumeId)
                        .stream().map(ResumeMapper::toCertificationResponse)
                        .toList();

        List<AwardResponse> awardResponses=
                awardRepository.findByResumeIdOrderByDisplayOrderAsc(resumeId)
                        .stream().map(ResumeMapper::toAwardResponse)
                        .toList();

        return ResumeMapper.toResumeResponse(
                resume,
                workExperienceResponses,
                educationResponses,
                resumeSkillResponses,
                projectResponses,
                languageResponses,
                certificationResponses,
                awardResponses);
    }

    private void assertOwner(Resume resume, Long candidateId) {
        if (!resume.getCandidateId().equals(candidateId)) {
            throw new IllegalArgumentException("candidate id does not match");
        }
    }
}
