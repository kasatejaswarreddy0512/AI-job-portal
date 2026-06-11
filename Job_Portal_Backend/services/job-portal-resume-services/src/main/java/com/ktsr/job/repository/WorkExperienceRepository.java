package com.ktsr.job.repository;

import com.ktsr.job.model.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Long> {

    List<WorkExperience> findByResumeIdOrderByDisplayOrderAsc(Long resumeId);

}
