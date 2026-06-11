package com.ktsr.job.service;

import com.ktsr.job.dto.LanguageResponse;
import com.ktsr.job.payload.AddLanguageRequest;

import java.util.List;

public interface LanguageService {

    LanguageResponse addLanguage(Long resumeId, Long candidateId, AddLanguageRequest request);

    List<LanguageResponse> getLanguages(Long resumeId);

    LanguageResponse updateLanguage(Long languageId,Long resumeId, Long candidateId, AddLanguageRequest request);

    void deleteLanguage(Long languageId, Long resumeId, Long candidateId);
}
