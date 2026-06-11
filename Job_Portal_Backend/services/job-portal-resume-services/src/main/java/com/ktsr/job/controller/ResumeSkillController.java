package com.ktsr.job.controller;

import com.ktsr.job.dto.ApiResponse;
import com.ktsr.job.dto.ResumeSkillResponse;
import com.ktsr.job.payload.ResumeSkillRequest;
import com.ktsr.job.service.ResumeSkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resume/{resumeId}/skills")
@RequiredArgsConstructor
public class ResumeSkillController {

    private final ResumeSkillService resumeSkillService;

    @PostMapping
    public ResponseEntity<ResumeSkillResponse> addSkill(@PathVariable Long resumeId,
                                                        @RequestHeader("X-User-Id") Long candidateId,
                                                        @RequestBody @Valid ResumeSkillRequest request){
        return new ResponseEntity<>(resumeSkillService.addSkill(resumeId, candidateId, request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ResumeSkillResponse>> getAllSkills(@PathVariable Long resumeId){
        return new ResponseEntity<>(resumeSkillService.getSkills(resumeId), HttpStatus.OK);
    }

    @PutMapping("/{skillId}")
    public ResponseEntity<ResumeSkillResponse> updateSkill(@PathVariable Long skillId,
                                                           @PathVariable Long resumeId,
                                                           @RequestHeader("X-User-Id") Long candidateId,
                                                           @RequestBody @Valid ResumeSkillRequest request
                                                           ){
        return ResponseEntity.ok(resumeSkillService.updateSkill(skillId, resumeId, candidateId, request));
    }

    @DeleteMapping("/{skillId}")
    public ResponseEntity<ApiResponse> deleteSkill(@PathVariable Long skillId,
                                                   @PathVariable Long resumeId,
                                                   @RequestHeader("X-User-Id") Long candidateId){
        resumeSkillService.deleteSkill(skillId, resumeId, candidateId);
        return ResponseEntity.ok(new ApiResponse("Skill deleted successfully...!", true));
    }
}
