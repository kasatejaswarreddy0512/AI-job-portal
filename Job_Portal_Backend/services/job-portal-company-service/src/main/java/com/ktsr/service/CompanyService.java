package com.ktsr.service;

import com.ktsr.job.domain.CompanyStatus;
import com.ktsr.job.domain.CompanyType;
import com.ktsr.job.domain.IndustryType;
import com.ktsr.job.dto.CompanyRequest;
import com.ktsr.job.dto.CompanyResponse;
import com.ktsr.model.Company;

import java.util.List;

public interface CompanyService {

    CompanyResponse createCompany(Long ownerId, CompanyRequest request);
    CompanyResponse getCompanyById(Long id);
    CompanyResponse getMyCompany(Long ownerId);
    List<CompanyResponse> getAllCompanies(CompanyType companyType,
                                          IndustryType industryType,
                                          CompanyStatus companyStatus);

    CompanyResponse updateCompany(Long companyId ,Long ownerId, CompanyRequest req);

    CompanyResponse verifyCompany(Long companyId);

    void deleteCompany(Long id, Long ownerId);
    CompanyResponse deactivateCompany(Long companyId);

    Company getCompanyEntityById(Long id);



}
