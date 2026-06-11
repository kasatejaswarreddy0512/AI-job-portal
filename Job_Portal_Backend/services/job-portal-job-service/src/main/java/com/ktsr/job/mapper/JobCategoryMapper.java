package com.ktsr.job.mapper;

import com.ktsr.job.dto.JobCategoryResponse;
import com.ktsr.job.model.JobCategory;

import java.util.List;
import java.util.stream.Collectors;

public class JobCategoryMapper {

    public static JobCategoryResponse toResponse(JobCategory jobCategory,
                                                 boolean includeChildren) {

        List<JobCategoryResponse> subCategories =null;

        if(includeChildren && jobCategory.getSubCategories() != null){
            subCategories=jobCategory.getSubCategories().stream().map(
                    sub -> toResponse(sub, false)
            ).collect(Collectors.toList());

        }

        return JobCategoryResponse.builder()
                .id(jobCategory.getId())
                .name(jobCategory.getName())
                .slug(jobCategory.getSlug())
                .description(jobCategory.getDescription())
                .iconUrl(jobCategory.getIconUrl())
                .active(jobCategory.isActive())
                .parentId(jobCategory.getParent() != null ? jobCategory.getParent().getId() : null)
                .parentName(jobCategory.getParent() != null ? jobCategory.getParent().getName() : null)
                .subCategories(subCategories)
                .createdAt(jobCategory.getCreatedAt())
                .build();

    }
}
