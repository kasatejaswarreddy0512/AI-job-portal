package com.ktsr.job.service.impl;

import com.ktsr.job.dto.LanguageResponse;
import com.ktsr.job.mapper.ResumeMapper;
import com.ktsr.job.model.Language;
import com.ktsr.job.model.Resume;
import com.ktsr.job.payload.AddLanguageRequest;
import com.ktsr.job.repository.LanguageRepository;
import com.ktsr.job.service.LanguageService;
import com.ktsr.job.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;
    private final ResumeService resumeService;

    @Override
    public LanguageResponse addLanguage(Long resumeId, Long candidateId, AddLanguageRequest request) {
        Resume resume= resumeService.getResumeEntity(resumeId);
        assertOwner(resume, candidateId);

        Language language=Language.builder()
                .resume(resume)
                .languageName(request.getLanguageName())
                .proficiency(request.getProficiency())
                .displayOrders(request.getDisplayOrders() !=null ? request.getDisplayOrders():0)
                .build();

        Language saved= languageRepository.save(language);
        return ResumeMapper.toLanguageResponse(saved);
    }

    @Override
    public List<LanguageResponse> getLanguages(Long resumeId) {
        return languageRepository.findByResumeIdOrderByDisplayOrdersAsc(resumeId)
                .stream().map(ResumeMapper::toLanguageResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LanguageResponse updateLanguage(Long languageId, Long resumeId, Long candidateId, AddLanguageRequest request) {

        Language language=languageRepository.findById(languageId)
                .orElseThrow(()-> new RuntimeException("Language not found"));

        assertOwner(language.getResume(),candidateId);

        language.setLanguageName(request.getLanguageName());
        language.setProficiency(request.getProficiency());
        if (request.getDisplayOrders() != null) language.setDisplayOrders(request.getDisplayOrders());

        Language updated= languageRepository.save(language);

        return ResumeMapper.toLanguageResponse(updated);
    }

    @Override
    public void deleteLanguage(Long languageId, Long resumeId, Long candidateId) {
        Language language=languageRepository.findById(languageId)
                .orElseThrow(()-> new RuntimeException("Language not found"));
        assertOwner(language.getResume(),candidateId);
        languageRepository.delete(language);
    }


    private void assertOwner(Resume resume, Long candidateId) {
        if (!resume.getCandidateId().equals(candidateId)) {
            throw new IllegalArgumentException("candidate id does not match");
        }
    }
}
