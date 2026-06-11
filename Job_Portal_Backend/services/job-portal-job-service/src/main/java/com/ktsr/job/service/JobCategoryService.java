package com.ktsr.job.service;


import com.ktsr.job.dto.JobCategoryResponse;
import com.ktsr.job.model.JobCategory;
import com.ktsr.job.payload.JobCategoryRequest;

import java.util.List;

public interface JobCategoryService {

    JobCategoryResponse createCategory(JobCategoryRequest request);

    List<JobCategoryResponse> getAllCategories();

    JobCategoryResponse getCategoryById(Long id);

    JobCategoryResponse updateCategory(Long id, JobCategoryRequest request);

    void deleteCategoryById(Long id);

    JobCategory getCategoryEntityById(Long id);
}
