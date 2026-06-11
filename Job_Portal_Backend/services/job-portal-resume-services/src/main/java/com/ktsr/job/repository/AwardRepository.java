package com.ktsr.job.repository;

import com.ktsr.job.model.Award;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AwardRepository extends JpaRepository<Award,Long> {

    List<Award> findByResumeIdOrderByDisplayOrderAsc(Long resumeId);
}
