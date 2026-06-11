package com.ktsr.job.service.impl;

import com.ktsr.job.dto.JobCategoryResponse;
import com.ktsr.job.mapper.JobCategoryMapper;
import com.ktsr.job.model.JobCategory;
import com.ktsr.job.payload.JobCategoryRequest;
import com.ktsr.job.repository.JobCategoryRepository;
import com.ktsr.job.service.JobCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobCategoryServiceImpl implements JobCategoryService {

    private final JobCategoryRepository jobCategoryRepository;

    @Override
    public JobCategoryResponse createCategory(JobCategoryRequest request) {

        if (jobCategoryRepository.existsByName(request.getName())) {
            throw new RuntimeException("Category name already exists, choose different name");
        }
        JobCategory parent = null;
        if (request.getParentId() != null) {
            parent = getCategoryEntityById(request.getParentId());
        }
        String slug = generateUniqueSlug(request.getName());

        JobCategory category = JobCategory.builder()
                .name(request.getName())
                .slug(slug)
                .iconUrl(request.getIconUrl())
                .description(request.getDescription())
                .parent(parent)
                .build();

        JobCategory savedCategory = jobCategoryRepository.save(category);
        return JobCategoryMapper.toResponse(savedCategory, true);
    }

    @Override
    public List<JobCategoryResponse> getAllCategories() {
        return jobCategoryRepository.findByActiveTrue().stream()
                .map(c->JobCategoryMapper.toResponse(c,false))
                .collect(Collectors.toList());
    }

    @Override
    public JobCategoryResponse getCategoryById(Long id) {
        JobCategory jobCategory=getCategoryEntityById(id);
        return JobCategoryMapper.toResponse(jobCategory,false);
    }

    @Override
    public JobCategoryResponse updateCategory(Long id, JobCategoryRequest request) {
        JobCategory jobCategory= getCategoryEntityById(id);
        if(!jobCategory.getName().equals(request.getName()) &&
                jobCategoryRepository.existsByName(request.getName())){
            throw new RuntimeException("Category name already exists, choose different name");
        }

        JobCategory parent=null;
        if(request.getParentId()!=null){
            if (request.getParentId().equals(id)){
                throw new RuntimeException("Category cannot be its own parent..!");
            }
            parent=getCategoryEntityById(request.getParentId());
        }

        jobCategory.setName(request.getName());
        jobCategory.setDescription(request.getDescription());
        jobCategory.setIconUrl(request.getIconUrl());
        jobCategory.setParent(parent);

        return JobCategoryMapper.toResponse(jobCategoryRepository.save(jobCategory),true);
    }

    @Override
    public void deleteCategoryById(Long id) {
        JobCategory jobCategory =getCategoryEntityById(id);
        jobCategory.setActive(false);
        jobCategoryRepository.save(jobCategory);
        jobCategoryRepository.delete(jobCategory);
    }

    @Override
    public JobCategory getCategoryEntityById(Long id) {
        return jobCategoryRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Category Not Found..!"));

    }

    private String generateUniqueSlug(String name) {
        String base = name.toLowerCase()
                .replaceAll("[^a-z0-9\\s]", "")
                .trim().replaceAll("[\\s-]", "-");
        if (!jobCategoryRepository.existsBySlug(base)) {
            return base;
        }
        int counter = 1;
        while (jobCategoryRepository.existsBySlug(base + "-" + counter)) {
            counter++;
        }
        return base + "-" + counter;
    }
}
