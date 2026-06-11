package com.ktsr.job.controller;

import com.ktsr.job.dto.ApiResponse;
import com.ktsr.job.dto.LanguageResponse;
import com.ktsr.job.payload.AddLanguageRequest;
import com.ktsr.job.service.LanguageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resume/{resumeId}/language")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService languageService;

    @PostMapping
    public ResponseEntity<LanguageResponse> getLanguage(@PathVariable Long resumeId,
                                                        @RequestHeader("X-User-Id") Long candidateId,
                                                        @RequestBody @Valid AddLanguageRequest request){
        return new ResponseEntity<>(languageService.addLanguage(resumeId, candidateId, request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LanguageResponse>> getAllLanguages(@PathVariable Long resumeId){
        return ResponseEntity.ok(languageService.getLanguages(resumeId));
    }

    @PutMapping("/{languageId}")
    public ResponseEntity<LanguageResponse> updateLanguage(@PathVariable Long languageId,
                                                           @PathVariable Long resumeId,
                                                           @RequestHeader("X-User-Id") Long candidateId,
                                                           @RequestBody @Valid AddLanguageRequest request){
        return ResponseEntity.ok(languageService.updateLanguage(languageId, resumeId, candidateId, request));
    }

    @DeleteMapping("/{languageId}")
    public ResponseEntity<ApiResponse> deleteLanguage(@PathVariable Long languageId,
                                                      @PathVariable Long resumeId,
                                                      @RequestHeader("X-User-Id") Long candidateId){
        languageService.deleteLanguage(languageId, resumeId, candidateId);
        return ResponseEntity.ok(new ApiResponse("Language Deleted Successfully...!", true));
    }

}
