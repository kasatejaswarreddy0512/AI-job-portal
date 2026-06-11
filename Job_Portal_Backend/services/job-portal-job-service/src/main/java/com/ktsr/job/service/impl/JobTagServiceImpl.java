package com.ktsr.job.service.impl;

import com.ktsr.job.dto.JobTagResponse;
import com.ktsr.job.mapper.JobTagMapper;
import com.ktsr.job.model.JobTag;
import com.ktsr.job.payload.JobTagRequest;
import com.ktsr.job.repository.JobTagRepository;
import com.ktsr.job.service.JobTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobTagServiceImpl implements JobTagService {

    private final JobTagRepository jobTagRepository;

    @Override
    public JobTagResponse createTag(JobTagRequest request) {

        if (jobTagRepository.existsByName(request.getName())){
            throw new RuntimeException("Tag name already exists...!");
        }

        String slug= generateUniqueSlug(request.getName());

        JobTag jobTag=JobTag.builder()
                .name(request.getName())
                .slug(slug)
                .build();

        JobTag saved= jobTagRepository.save(jobTag);
        return JobTagMapper.jobTagResponse(saved);
    }

    @Override
    public List<JobTagResponse> getAllTags() {
        return jobTagRepository.findAll()
                .stream().map(JobTagMapper::jobTagResponse)
                .collect(Collectors.toList());
    }

    @Override
    public JobTagResponse getTagById(Long id) {
        JobTag jobTag= getTagEntityById(id);
        return JobTagMapper.jobTagResponse(jobTag);
    }

    @Override
    public JobTagResponse updateTag(Long id, JobTagRequest request) {
        JobTag tag= getTagEntityById(id);

        if(!tag.getName().equals(request.getName()) && jobTagRepository.existsByName(request.getName())){
            throw new RuntimeException("Tag already Exists...!");
        }

        tag.setName(request.getName());

        return JobTagMapper.jobTagResponse(jobTagRepository.save(tag));
    }

    @Override
    public void deleteTag(Long id) {
        JobTag jobTag=getTagEntityById(id);
        jobTagRepository.delete(jobTag);
    }

    @Override
    public JobTag getTagEntityById(Long id) {
        return jobTagRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Tag Not Found...!"));
    }

    @Override
    public Set<JobTag> getTasByIds(Set<Long> ids) {
        Set<JobTag> tags=new HashSet<>(jobTagRepository.findAllById(ids));
        return tags;
    }

    private String generateUniqueSlug(String name) {
        String base = name.toLowerCase()
                .replaceAll("[^a-z0-9\\s]", "")
                .trim().replaceAll("[\\s-]", "-");
        if (!jobTagRepository.existsBySlug(base)) {
            return base;
        }
        int counter = 1;
        while (jobTagRepository.existsBySlug(base + "-" + counter)) {
            counter++;
        }
        return base + "-" + counter;
    }
}
