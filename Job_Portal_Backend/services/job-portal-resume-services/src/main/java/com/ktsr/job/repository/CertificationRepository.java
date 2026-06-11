package com.ktsr.job.repository;

import com.ktsr.job.model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificationRepository extends JpaRepository<Certification,Long> {

    List<Certification> findByResumeIdOrderByDisplayOrderAsc(Long resumeId);
}
