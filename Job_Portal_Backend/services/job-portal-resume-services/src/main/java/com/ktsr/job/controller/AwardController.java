package com.ktsr.job.controller;

import com.ktsr.job.dto.ApiResponse;
import com.ktsr.job.dto.AwardResponse;
import com.ktsr.job.model.Award;
import com.ktsr.job.payload.AddAwardRequest;
import com.ktsr.job.repository.AwardRepository;
import com.ktsr.job.service.AwardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resume/{resumeId}/awards")
@RequiredArgsConstructor
public class AwardController {

    private final AwardService awardService;

    @PostMapping
    public ResponseEntity<AwardResponse> addAward(@PathVariable Long resumeId,
                                                  @RequestHeader("X-User-Id") Long candidateId,
                                                  @RequestBody @Valid AddAwardRequest request){
        return new ResponseEntity<>(awardService.addAward(resumeId, candidateId, request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AwardResponse>> getAllAwards(@PathVariable Long resumeId){
        return new ResponseEntity<>(awardService.getAwards(resumeId), HttpStatus.OK);
    }

    @PutMapping("/{awardId}")
    public ResponseEntity<AwardResponse> updateAward(@PathVariable Long awardId,
                                                     @PathVariable Long resumeId,
                                                     @RequestHeader("X-User-Id") Long candidateId,
                                                     @RequestBody @Valid AddAwardRequest request){
        return ResponseEntity.ok(awardService.updateAward(awardId, resumeId, candidateId, request));
    }

    @DeleteMapping("/{awardId}")
    public ResponseEntity<ApiResponse> deleteAward(@PathVariable Long awardId,
                                                   @PathVariable Long resumeId,
                                                   @RequestHeader("X-User-Id") Long candidateId){
        awardService.deleteAward(awardId, resumeId, candidateId);
        return ResponseEntity.ok(new ApiResponse("Award Deleted Successfully..!", true));
    }



}
