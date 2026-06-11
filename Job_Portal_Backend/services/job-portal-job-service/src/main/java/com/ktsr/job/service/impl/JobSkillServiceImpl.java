package com.ktsr.job.service.impl;

import com.ktsr.job.dto.JobSkillResponse;
import com.ktsr.job.mapper.JobSkillMapper;
import com.ktsr.job.model.JobSkill;
import com.ktsr.job.payload.JobSkillRequest;
import com.ktsr.job.repository.JobSkillRepository;
import com.ktsr.job.service.JobSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobSkillServiceImpl implements JobSkillService {

    private final JobSkillRepository jobSkillRepository;

    @Override
    public JobSkillResponse createSkill(JobSkillRequest request) {
        if(jobSkillRepository.existsByName(request.getName())){
            throw new RuntimeException("Skill name already exists..!");
        }
        String slug= generateUniqueSlug(request.getName());

        JobSkill skill=JobSkill.builder()
                .name(request.getName())
                .slug(slug)
                .category(request.getCategory())
                .active(true)
                .build();

        JobSkill savedSkill=jobSkillRepository.save(skill);
        return JobSkillMapper.toJobSkillResponse(savedSkill);
    }

    @Override
    public List<JobSkillResponse> getAllSkills() {
        return jobSkillRepository.findByActiveTrue()
                .stream().map(JobSkillMapper::toJobSkillResponse)
                .collect(Collectors.toList());
    }

    @Override
    public JobSkillResponse getSkillById(Long id) {
        JobSkill jobSkill= jobSkillRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Skill Not Found..!"));
        return JobSkillMapper.toJobSkillResponse(jobSkill);
    }

    @Override
    public JobSkillResponse updateSkill(Long id, JobSkillRequest request) {
        JobSkill skill= jobSkillRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Skill Not Found..!"));

        if(!skill.getName().equals(request.getName()) && jobSkillRepository.existsByName(request.getName())){
            throw new RuntimeException("Skill Name Already Exists..!");
        }

        skill.setName(request.getName());
        skill.setCategory(request.getCategory());

        JobSkill updated= jobSkillRepository.save(skill);

        return JobSkillMapper.toJobSkillResponse(updated);
    }

    @Override
    public void deleteSkill(Long id) {
        JobSkill skill= jobSkillRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Skill Not Found..!"));
        skill.setActive(false);
        jobSkillRepository.save(skill);
    }

    @Override
    public Set<JobSkill> getSkillByIds(Set<Long> ids) {
        Set<JobSkill> skills= new HashSet<>(jobSkillRepository.findAllById(ids));
        return skills;
    }


    private String generateUniqueSlug(String name) {
        String base = name.toLowerCase()
                .replaceAll("[^a-z0-9\\s]", "")
                .trim().replaceAll("[\\s-]", "-");
        if (!jobSkillRepository.existsBySlug(base)) {
            return base;
        }
        int counter = 1;
        while (jobSkillRepository.existsBySlug(base + "-" + counter)) {
            counter++;
        }
        return base + "-" + counter;
    }
}
