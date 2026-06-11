package com.ktsr.job.repository;

import com.ktsr.job.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume,Long> {

    List<Resume> findByCandidateIdAndIsActiveTrue(Long candidateId);

    Optional<Resume> findByCandidateIdAndIsDefaultTrue(Long candidateId);

}
