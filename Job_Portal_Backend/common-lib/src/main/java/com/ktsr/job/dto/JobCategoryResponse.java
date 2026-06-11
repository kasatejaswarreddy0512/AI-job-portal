package com.ktsr.job.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class JobCategoryResponse {

    private Long id;
    private String name;
    private String slug;
    private String description;
    private String iconUrl;
    private boolean active= true;

    private Long parentId;
    private String parentName;

    private List<JobCategoryResponse> subCategories;


    private LocalDateTime createdAt;
}
