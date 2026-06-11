package com.ktsr.job.controller;

import com.ktsr.job.dto.ApiResponse;
import com.ktsr.job.dto.JobTagResponse;
import com.ktsr.job.payload.JobTagRequest;
import com.ktsr.job.service.JobTagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
@RequiredArgsConstructor
public class JobTagController {

    private final JobTagService jobTagService;

    @PostMapping
    public ResponseEntity<JobTagResponse> createTag(@RequestBody @Valid JobTagRequest jobTagRequest){
        return new ResponseEntity<>(jobTagService.createTag(jobTagRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<JobTagResponse>> getAllTags(){
        return  ResponseEntity.ok(jobTagService.getAllTags());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobTagResponse> getTagById(@PathVariable Long id){
        return ResponseEntity.ok(jobTagService.getTagById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobTagResponse> updateTag(@PathVariable Long id,
                                                    @RequestBody @Valid JobTagRequest request){
        return ResponseEntity.ok(jobTagService.updateTag(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTag(@PathVariable Long id){
        jobTagService.deleteTag(id);
        return ResponseEntity.ok(new ApiResponse("Job Tag Deleted Successfully...!", true));
    }
}
