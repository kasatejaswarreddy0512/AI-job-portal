package com.ktsr.job.service.impl;

import com.ktsr.job.domain.JobStatus;
import com.ktsr.job.dto.CompanyResponse;
import com.ktsr.job.dto.JobRequest;
import com.ktsr.job.dto.JobResponse;
import com.ktsr.job.mapper.JobMapper;
import com.ktsr.job.model.*;
import com.ktsr.job.payload.JobSearchRequest;
import com.ktsr.job.repository.JobRepository;
import com.ktsr.job.repository.JobSpecification;
import com.ktsr.job.service.JobCategoryService;
import com.ktsr.job.service.JobService;
import com.ktsr.job.service.JobSkillService;
import com.ktsr.job.service.JobTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private  final JobCategoryService categoryService;
    private  final JobSkillService skillService;
    private final JobTagService tagService;


    @Override
    public JobResponse createJob(Long employerId, JobRequest request) {


        JobCategory category=categoryService.getCategoryEntityById(request.getCategoryId());

        Set<JobSkill> skills=request.getSkillIds()!=null
                ? skillService.getSkillByIds(request.getSkillIds())
                : Collections.emptySet();

        Set<JobTag> tags=request.getTagIds()!=null ?
                tagService.getTasByIds(request.getTagIds())
                :Collections.emptySet();


        Long companyId = 1L;
        Job job = Job.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .requirements(request.getRequirements())
                .responsibilities(request.getResponsibilities())
                .benefits(request.getBenefits())
                .companyId(companyId)
                .employerId(employerId)
                .category(category)
                .skills(skills)
                .tags(tags)
                .location(buildLocation(request))
                .salaryRange(buildSalaryRange(request))
                .jobType(request.getJobType())
                .workMode(request.getWorkMode())
                .experienceLevel(request.getExperienceLevel())
                .openings(request.getOpenings() != null ? request.getOpenings() : 1)
                .applicationDeadLine(request.getApplicationDeadLine())
                .expiresAt(request.getExpiresAt())
                .status(request.getStatus())
                .active(true)
                .build();

        Job savedJob = jobRepository.save(job);
        return convertToResponse(savedJob);
    }

    @Override
    public JobResponse getJobById(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job Not found...!"));
        return convertToResponse(job);
    }

    @Override
    public List<JobResponse> getJobs(JobSearchRequest request) {
        List<Job> jobs = jobRepository.findAll(JobSpecification.build(request));
        return jobs.stream().map(
                job -> convertToResponse(job)
        ).collect(Collectors.toList());
    }

    @Override
    public List<JobResponse> getJobByCompany(Long companyId) {
        List<Job> jobs = jobRepository.findByCompanyId(companyId);
        return jobs.stream().map(
                job -> convertToResponse(job)
        ).collect(Collectors.toList());
    }

    @Override
    public JobResponse updateJob(Long jobId, Long employerId, JobRequest request) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job Not found...!"));


        JobCategory category=categoryService.getCategoryEntityById(request.getCategoryId());

        Set<JobSkill> skills=request.getSkillIds()!=null
                ? skillService.getSkillByIds(request.getSkillIds())
                : Collections.emptySet();

        Set<JobTag> tags=request.getTagIds()!=null ?
                tagService.getTasByIds(request.getTagIds())
                :Collections.emptySet();

        assertEmployer(job, employerId);

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setRequirements(request.getRequirements());
        job.setResponsibilities(request.getResponsibilities());
        job.setBenefits(request.getBenefits());
        job.setCategory(category);
        job.setSkills(skills);
        job.setTags(tags);
        job.setLocation(buildLocation(request));
        job.setSalaryRange(buildSalaryRange(request));
        job.setJobType(request.getJobType());
        job.setWorkMode(request.getWorkMode());
        job.setExperienceLevel(request.getExperienceLevel());
        job.setOpenings(request.getOpenings() != null ? request.getOpenings() : job.getOpenings());
        job.setApplicationDeadLine(request.getApplicationDeadLine());
        job.setExpiresAt(request.getExpiresAt());
        return convertToResponse(jobRepository.save(job));
    }

    @Override
    public JobResponse publishJobs(Long jobId, Long employerId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job Not found...!"));

        assertEmployer(job, employerId);
        if (job.getStatus() == JobStatus.CLOSE || job.getStatus() == JobStatus.EXPIRED) {
            throw new RuntimeException("Job is Expired...!");
        }
        job.setStatus(JobStatus.OPEN);
        job.setPublishedAt(LocalDateTime.now());
        job.setActive(true);
        return convertToResponse(jobRepository.save(job));
    }

    @Override
    public JobResponse closeJobs(Long jobId, Long employerId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job Not found...!"));

        assertEmployer(job, employerId);

        job.setStatus(JobStatus.CLOSE);
        job.setCloseAt(LocalDateTime.now());
        job.setActive(false);
        return convertToResponse(jobRepository.save(job));

    }

    @Override
    public void deleteJob(Long jobId, Long employerId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job Not found...!"));
        assertEmployer(job, employerId);

        jobRepository.delete(job);

    }

    @Override
    public List<JobResponse> getAllJobsAdmin() {
        return jobRepository.findAll().stream().map(
                this::convertToResponse
        ).collect(Collectors.toList());
    }


    private JobResponse convertToResponse(Job savedJob) {

        CompanyResponse companyResponse = CompanyResponse.builder()
                .id(savedJob.getCompanyId())
                .build();
        return JobMapper.toResponse(savedJob, companyResponse);
    }

    private SalaryRange buildSalaryRange(JobRequest request) {
        return SalaryRange.builder()
                .minSalary(request.getMinSalary())
                .maxSalary(request.getMaxSalary())
                .build();
    }

    private JobLocation buildLocation(JobRequest request) {
        return JobLocation.builder()
                .address(request.getAddress())
                .city(request.getCity())
                .state(request.getState())
                .country(request.getCountry())
                .zipCode(request.getZipCode())
                .build();
    }

    private void assertEmployer(Job job, Long employerId) {
        if (!job.getEmployerId().equals(employerId)) {
            throw new RuntimeException("You are not the employer who posted this job..!");
        }
    }
}
