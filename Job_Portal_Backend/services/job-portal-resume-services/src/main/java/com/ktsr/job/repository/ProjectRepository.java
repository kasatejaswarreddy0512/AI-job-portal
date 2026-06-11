package com.ktsr.job.repository;

import com.ktsr.job.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {


    List<Project> findByResumeIdOrderByDisplayOrderAsc(Long resumeId);
}
