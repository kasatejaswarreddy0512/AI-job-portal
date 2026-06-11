package com.ktsr.job.service.impl;

import com.ktsr.job.dto.AwardResponse;
import com.ktsr.job.mapper.ResumeMapper;
import com.ktsr.job.model.Award;
import com.ktsr.job.model.Resume;
import com.ktsr.job.payload.AddAwardRequest;
import com.ktsr.job.repository.AwardRepository;
import com.ktsr.job.service.AwardService;
import com.ktsr.job.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AwardServiceImpl implements AwardService {

    private final AwardRepository awardRepository;
    private final ResumeService resumeService;

    @Override
    public AwardResponse addAward(Long resumeId, Long candidateId, AddAwardRequest request) {
        Resume resume = resumeService.getResumeEntity(resumeId);
        assertOwner(resume, candidateId);

        Award award = Award.builder()
                .resume(resume)
                .awardTitle(request.getAwardTitle())
                .issuingOrganization(request.getIssuingOrganization())
                .awardDate(request.getAwardDate())
                .description(request.getDescription())
                .displayOrder(request.getDisplayOrder() != null ? request.getDisplayOrder() : 0)
                .build();

        Award savedAward = awardRepository.save(award);
        return ResumeMapper.toAwardResponse(savedAward);
    }

    @Override
    public List<AwardResponse> getAwards(Long resumeId) {
        return awardRepository.findByResumeIdOrderByDisplayOrderAsc(resumeId)
                .stream().map(ResumeMapper::toAwardResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AwardResponse updateAward(Long awardId, Long resumeId, Long candidateId, AddAwardRequest request) {
        Award award = awardRepository.findById(awardId)
                .orElseThrow(() -> new RuntimeException("Award Not Found"));
        assertOwner(award.getResume(), candidateId);

        award.setAwardTitle(request.getAwardTitle());
        award.setIssuingOrganization(request.getIssuingOrganization());
        award.setAwardDate(request.getAwardDate());
        award.setDescription(request.getDescription());
        award.setDisplayOrder(request.getDisplayOrder() != null ? request.getDisplayOrder() : 0);

        Award updatedAward = awardRepository.save(award);
        return ResumeMapper.toAwardResponse(updatedAward);
    }

    @Override
    public void deleteAward(Long awardId, Long resumeId, Long candidateId) {
        Award award = awardRepository.findById(awardId)
                .orElseThrow(() -> new RuntimeException("Award Not Found"));
        assertOwner(award.getResume(), candidateId);
        awardRepository.delete(award);
    }

    private void assertOwner(Resume resume, Long candidateId) {
        if (!resume.getCandidateId().equals(candidateId)) {
            throw new IllegalArgumentException("candidate id does not match");
        }
    }
}
