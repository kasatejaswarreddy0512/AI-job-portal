package com.ktsr.job.service;

import com.ktsr.job.dto.AwardResponse;
import com.ktsr.job.payload.AddAwardRequest;

import java.util.List;

public interface AwardService {

    AwardResponse addAward(Long resumeId, Long candidateId, AddAwardRequest request);

    List<AwardResponse> getAwards(Long resumeId);

    AwardResponse updateAward(Long awardId,Long resumeId, Long candidateId, AddAwardRequest request);

    void deleteAward(Long awardId, Long resumeId, Long candidateId);
}
