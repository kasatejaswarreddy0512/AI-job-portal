package com.ktsr.job.repository;

import com.ktsr.job.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Long> {

    List<Education> findByResumeIdOrderByDisplayOrdersAsc(Long resumeId);

}
