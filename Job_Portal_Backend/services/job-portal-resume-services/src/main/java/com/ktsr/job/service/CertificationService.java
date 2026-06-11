package com.ktsr.job.service;

import com.ktsr.job.dto.AddCertificationRequest;
import com.ktsr.job.dto.CertificationResponse;

import java.util.List;


public interface CertificationService {

    CertificationResponse addCertificate(Long resumeId, Long candidateId, AddCertificationRequest request);

    List<CertificationResponse> getAllCertifications(Long resumeId);

    CertificationResponse updateCertificate(Long certificationId,Long resumeId, Long candidateId, AddCertificationRequest request);

    void deleteCertificate(Long certificationId,Long resumeId, Long candidateId);
}
