package com.ktsr.job.repository;

import com.ktsr.job.model.JobSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobSkillRepository extends JpaRepository<JobSkill, Long> {

    List<JobSkill> findByActiveTrue();

    boolean existsByName(String name);

    boolean existsBySlug(String slug);

}
