package com.ktsr.job.repository;

import com.ktsr.job.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language,Long> {

    List<Language> findByResumeIdOrderByDisplayOrdersAsc(Long resumeId);

}
