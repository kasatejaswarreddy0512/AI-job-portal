package com.ktsr.job.repository;

import com.ktsr.job.model.JobTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobTagRepository extends JpaRepository<JobTag , Long> {

    boolean existsByName(String name);

    boolean existsBySlug(String slug);


}
