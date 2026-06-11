package com.ktsr.job.controller;

import com.ktsr.job.dto.ApiResponse;
import com.ktsr.job.dto.JobSkillResponse;
import com.ktsr.job.payload.JobSkillRequest;
import com.ktsr.job.service.JobSkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skill")
@RequiredArgsConstructor
public class JobSkillController {

    private  final JobSkillService jobSkillService;

    @PostMapping
    public ResponseEntity<JobSkillResponse> createSkill(@RequestBody @Valid JobSkillRequest request){
        return new ResponseEntity<>(jobSkillService.createSkill(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<JobSkillResponse>> getAllSkills(){
        return ResponseEntity.ok(jobSkillService.getAllSkills());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobSkillResponse> getSkillById(@PathVariable Long id){
        return ResponseEntity.ok(jobSkillService.getSkillById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobSkillResponse> updateSkill(@PathVariable Long id,
                                                        @RequestBody @Valid JobSkillRequest request){
        return ResponseEntity.ok(jobSkillService.updateSkill(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteSkill(@PathVariable Long id){
        jobSkillService.deleteSkill(id);
        return ResponseEntity.ok(new ApiResponse("Skill deleted Successfully...!", true));
    }

}
