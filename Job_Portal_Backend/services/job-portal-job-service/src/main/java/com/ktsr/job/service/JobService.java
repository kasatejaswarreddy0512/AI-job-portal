package com.ktsr.job.service;

import com.ktsr.job.dto.JobRequest;
import com.ktsr.job.dto.JobResponse;
import com.ktsr.job.model.Job;
import com.ktsr.job.payload.JobSearchRequest;

import java.util.List;

public interface JobService {

    JobResponse createJob(Long employerId, JobRequest job);

    JobResponse getJobById(Long id);

    List<JobResponse> getJobs(JobSearchRequest request);

    List<JobResponse> getJobByCompany(Long companyId);

    JobResponse updateJob(Long jobId, Long employerId, JobRequest request);

    JobResponse publishJobs(Long jobId, Long employerId);

    JobResponse closeJobs(Long jobId, Long employerId);

    void deleteJob(Long jobId, Long employerId);



    List<JobResponse> getAllJobsAdmin();
}
