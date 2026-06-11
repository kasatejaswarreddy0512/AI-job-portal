package com.ktsr.job.service.impl;

import com.ktsr.job.dto.EducationResponse;
import com.ktsr.job.mapper.ResumeMapper;
import com.ktsr.job.model.Education;
import com.ktsr.job.model.Resume;
import com.ktsr.job.payload.EducationRequest;
import com.ktsr.job.repository.EducationRepository;
import com.ktsr.job.service.EducationService;
import com.ktsr.job.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;
    private final ResumeService resumeService;

    @Override
    public EducationResponse addEducation(Long resumeId, Long candidateId, EducationRequest request) {
        Resume resume = resumeService.getResumeEntity(resumeId);
        assertOwner(resume,candidateId);

        Education education= Education.builder()
                .resume(resume)
                .instituteName(request.getInstituteName())
                .degree(request.getDegree())
                .fieldOfStudy(request.getFieldOfStudy())
                .grade(request.getGrade())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .isCurrentlyStudying(Boolean.TRUE.equals(request.getIsCurrentlyStudying()))
                .description(request.getDescription())
                .displayOrders(request.getDisplayOrders() !=null ? request.getDisplayOrders():0)
                .build();

        Education saved= educationRepository.save(education);
        return ResumeMapper.toEducationResponse(saved);
    }

    @Override
    public List<EducationResponse> getEducations(Long resumeId) {
        return educationRepository.findByResumeIdOrderByDisplayOrdersAsc(resumeId)
                .stream().map(ResumeMapper::toEducationResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EducationResponse updateEducation(Long educationId, Long resumeId, Long candidateId, EducationRequest request) {
        Education education= educationRepository.findById(educationId)
                .orElseThrow(()-> new RuntimeException("Education not found"));
        assertOwner(education.getResume(),candidateId);

        education.setInstituteName(request.getInstituteName());
        education.setDegree(request.getDegree());
        education.setFieldOfStudy(request.getFieldOfStudy());
        education.setGrade(request.getGrade());
        education.setStartDate(request.getStartDate());
        education.setEndDate(request.getEndDate());
        education.setIsCurrentlyStudying(Boolean.TRUE.equals(request.getIsCurrentlyStudying()));
        education.setDescription(request.getDescription());
        if (request.getDisplayOrders() != null) education.setDisplayOrders(request.getDisplayOrders());

        Education updated= educationRepository.save(education);

        return ResumeMapper.toEducationResponse(updated);
    }

    @Override
    public void deleteEducation(Long educationId, Long resumeId, Long candidateId) {
        Education education= educationRepository.findById(educationId)
                .orElseThrow(()-> new RuntimeException("Education not found"));
        assertOwner(education.getResume(),candidateId);
        educationRepository.delete(education);
    }

    private void assertOwner(Resume resume, Long candidateId) {
        if (!resume.getCandidateId().equals(candidateId)) {
            throw new IllegalArgumentException("candidate id does not match");
        }
    }
}
