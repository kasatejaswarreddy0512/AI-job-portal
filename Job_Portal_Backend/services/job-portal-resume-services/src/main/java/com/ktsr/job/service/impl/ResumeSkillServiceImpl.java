package com.ktsr.job.service.impl;

import com.ktsr.job.dto.ResumeSkillResponse;
import com.ktsr.job.mapper.ResumeMapper;
import com.ktsr.job.model.Resume;
import com.ktsr.job.model.ResumeSkill;
import com.ktsr.job.payload.ResumeSkillRequest;
import com.ktsr.job.repository.ResumeSkillRepository;
import com.ktsr.job.service.ResumeService;
import com.ktsr.job.service.ResumeSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResumeSkillServiceImpl implements ResumeSkillService {

    private final ResumeSkillRepository resumeSkillRepository;
    private final ResumeService resumeService;

    @Override
    public ResumeSkillResponse addSkill(Long resumeId, Long candidateId, ResumeSkillRequest request) {
        Resume resume=resumeService.getResumeEntity(resumeId);
        assertOwner(resume, candidateId);

        ResumeSkill skill=ResumeSkill.builder()
                .resume(resume)
                .skillName(request.getSkillName())
                .proficiencyLevel(request.getProficiencyLevel())
                .yearsOfExperience(request.getYearsOfExperience())
                .displayOrder(request.getDisplayOrder())
                .build();

        ResumeSkill saved=resumeSkillRepository.save(skill);

        return ResumeMapper.toResumeSkillResponse(saved);
    }

    @Override
    public List<ResumeSkillResponse> getSkills(Long resumeId) {
        return resumeSkillRepository.findByResumeIdOrderByDisplayOrderAsc(resumeId)
                .stream().map(ResumeMapper::toResumeSkillResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ResumeSkillResponse updateSkill(Long skillId, Long resumeId, Long candidateId, ResumeSkillRequest request) {
        ResumeSkill skill=resumeSkillRepository.findById(skillId)
                .orElseThrow(()->new RuntimeException("Skill Not Found..!"));
        assertOwner(skill.getResume(), candidateId);

        skill.setSkillName(request.getSkillName());
        skill.setProficiencyLevel(request.getProficiencyLevel());
        skill.setYearsOfExperience(request.getYearsOfExperience());
        if (request.getDisplayOrder()!=null) skill.setDisplayOrder(request.getDisplayOrder());
        ResumeSkill updated=resumeSkillRepository.save(skill);
        return ResumeMapper.toResumeSkillResponse(updated);
    }

    @Override
    public void deleteSkill(Long resumeId, Long skillId, Long candidateId) {
        ResumeSkill skill=resumeSkillRepository.findById(skillId)
                .orElseThrow(()->new RuntimeException("Skill Not Found..!"));
        assertOwner(skill.getResume(), candidateId);
        resumeSkillRepository.delete(skill);
    }

    private void assertOwner(Resume resume, Long candidateId) {
        if (!resume.getCandidateId().equals(candidateId)) {
            throw new RuntimeException("Resume candidate id not match");
        }
    }
}
