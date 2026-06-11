package com.ktsr.controller;

import com.ktsr.job.domain.CompanyStatus;
import com.ktsr.job.domain.CompanyType;
import com.ktsr.job.domain.IndustryType;
import com.ktsr.job.dto.ApiResponse;
import com.ktsr.job.dto.CompanyRequest;
import com.ktsr.job.dto.CompanyResponse;
import com.ktsr.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyResponse> createCompany(
            @RequestHeader("X-User-Id") Long ownerId,
            @RequestBody @Valid CompanyRequest request){
        return new ResponseEntity<>(companyService.createCompany(ownerId,request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable Long id){
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }
    @GetMapping("/my")
    public ResponseEntity<CompanyResponse> getMyCompany(@RequestHeader("X-User-Id") Long ownerId){
        return ResponseEntity.ok(companyService.getMyCompany(ownerId));
    }

    @GetMapping
    public  ResponseEntity<List<CompanyResponse>> getAllCompanies(
            @RequestParam(required = false) CompanyType companyType,
            @RequestParam(required = false)IndustryType industryType,
            @RequestParam(required = false)CompanyStatus companyStatus){
        return  ResponseEntity.ok(companyService.getAllCompanies(companyType,industryType,companyStatus));
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<CompanyResponse> updateCompany(
            @PathVariable Long companyId,
            @RequestHeader("X-User-Id") Long ownerId,
            @RequestBody @Valid CompanyRequest companyRequest){
        return ResponseEntity.ok(companyService.updateCompany(companyId,ownerId,companyRequest));
    }

    @PatchMapping("/{id}/verify")
    public ResponseEntity<CompanyResponse> verifyCompany(@PathVariable Long id){
        return  ResponseEntity.ok(companyService.verifyCompany(id));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<CompanyResponse> deactivateCompany(@PathVariable Long id){
        return ResponseEntity.ok(companyService.deactivateCompany(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCompany(@PathVariable Long id,
                                                     @RequestHeader("X-User-Id") Long ownerId){
        companyService.deleteCompany(id,ownerId);
        return ResponseEntity.ok(new ApiResponse("Company Deleted Successfully...!", true));
    }


}
