package com.ktsr.job.service.impl;

import com.ktsr.job.dto.AddCertificationRequest;
import com.ktsr.job.dto.CertificationResponse;
import com.ktsr.job.mapper.ResumeMapper;
import com.ktsr.job.model.Certification;
import com.ktsr.job.model.Resume;
import com.ktsr.job.repository.CertificationRepository;
import com.ktsr.job.service.CertificationService;
import com.ktsr.job.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CertificationServiceImpl implements CertificationService {

    private final CertificationRepository certificationRepository;
    private final ResumeService resumeService;

    @Override
    public CertificationResponse addCertificate(Long resumeId, Long candidateId, AddCertificationRequest request) {
        Resume resume=resumeService.getResumeEntity(resumeId);
        assertOwner(resume,candidateId);

        Certification certification=Certification.builder()
                .resume(resume)
                .certificationName(request.getCertificationName())
                .issuingOrganization(request.getIssuingOrganization())
                .issueDate(request.getIssueDate())
                .expirationDate(request.getExpirationDate())
                .doesNotExpire(request.getDoesNotExpire())
                .description(request.getDescription())
                .displayOrder(request.getDisplayOrder() != null ? request.getDisplayOrder() : 0)
                .build();
        Certification savedCertification=certificationRepository.save(certification);

        return ResumeMapper.toCertificationResponse(savedCertification);
    }

    @Override
    public List<CertificationResponse> getAllCertifications(Long resumeId) {
        return certificationRepository.findByResumeIdOrderByDisplayOrderAsc(resumeId)
                .stream().map(ResumeMapper::toCertificationResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CertificationResponse updateCertificate(Long certificationId, Long resumeId, Long candidateId, AddCertificationRequest request) {
        Certification certification=certificationRepository.findById(certificationId)
                .orElseThrow(()-> new RuntimeException("certification not found"));
        assertOwner(certification.getResume(),candidateId);

        certification.setCertificationName(request.getCertificationName());
        certification.setIssuingOrganization(request.getIssuingOrganization());
        certification.setIssueDate(request.getIssueDate());
        certification.setExpirationDate(request.getExpirationDate());
        certification.setDoesNotExpire(request.getDoesNotExpire());
        certification.setDescription(request.getDescription());
        certification.setDisplayOrder(request.getDisplayOrder());
        Certification updatedCertification=certificationRepository.save(certification);
        return ResumeMapper.toCertificationResponse(updatedCertification);
    }

    @Override
    public void deleteCertificate(Long certificationId, Long resumeId, Long candidateId) {
        Certification certification=certificationRepository.findById(certificationId)
                .orElseThrow(()-> new RuntimeException("certification not found"));
        assertOwner(certification.getResume(),candidateId);
        certificationRepository.delete(certification);
    }

    private void assertOwner(Resume resume, Long candidateId) {
        if (!resume.getCandidateId().equals(candidateId)) {
            throw new IllegalArgumentException("candidate id does not match");
        }
    }
}
