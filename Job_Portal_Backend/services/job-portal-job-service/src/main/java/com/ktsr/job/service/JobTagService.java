package com.ktsr.job.service;

import com.ktsr.job.dto.JobTagResponse;
import com.ktsr.job.model.JobTag;
import com.ktsr.job.payload.JobTagRequest;

import java.util.List;
import java.util.Set;

public interface JobTagService {

    JobTagResponse createTag(JobTagRequest request);

    List<JobTagResponse> getAllTags();

    JobTagResponse getTagById(Long id);

    JobTagResponse updateTag(Long id, JobTagRequest request);

    void deleteTag(Long id);

    JobTag getTagEntityById(Long id);

    Set<JobTag> getTasByIds(Set<Long> ids);
}
