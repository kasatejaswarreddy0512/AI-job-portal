package com.ktsr.service.impl;

import com.ktsr.job.domain.CompanyStatus;
import com.ktsr.job.domain.CompanyType;
import com.ktsr.job.domain.IndustryType;
import com.ktsr.job.dto.CompanyRequest;
import com.ktsr.job.dto.CompanyResponse;
import com.ktsr.job.dto.SocialLinkResponse;
import com.ktsr.mapper.CompanyMapper;
import com.ktsr.model.Company;
import com.ktsr.model.SocialLinks;
import com.ktsr.repository.CompanyRepository;
import com.ktsr.service.CompanyService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public CompanyResponse createCompany(Long ownerId, CompanyRequest request) {

        if(companyRepository.existsByOwnerId(ownerId)){
            throw  new RuntimeException("You already have company register only one company per account i allowed...!" );
        }

        if (companyRepository.existsByName(request.getName())){
            throw  new RuntimeException("Company  already register. Please choose a different name...!");
        }

        if (request.getRegisterNumber()!=null && companyRepository.existsByRegisterNumber(request.getRegisterNumber())){
            throw  new RuntimeException("Company already exists. Please choose a different registration number...!");
        }

        String slug=generateSlug(request.getName());

        Company company=Company.builder()
                .name(request.getName())
                .slug(slug)
                .tagLine(request.getTagLine())
                .description(request.getDescription())
                .logoUrl(request.getLogoUrl())
                .coverImageUrl(request.getCoverImageUrl())
                .website(request.getWebsite())
                .email(request.getEmail())
                .phone(request.getPhone())
                .foundedYear(request.getFoundedYear())
                .companySize(request.getCompanySize())
                .companyType(request.getCompanyType())
                .industryType(request.getIndustryType())
                .registerNumber(request.getRegisterNumber())
                .ownerId(ownerId)
                .socialLinks(mapSocialLinks(request.getSocialLinks()))
                .build();
        Company saveCompany=companyRepository.save(company);
        return CompanyMapper.toResponse(saveCompany);
    }

    private List<SocialLinks> mapSocialLinks(List<SocialLinkResponse> socialLinks) {
        if(socialLinks==null || socialLinks.isEmpty()){
            return  new ArrayList<>();
        }
        return socialLinks.stream()
                .map(e-> SocialLinks.builder()
                        .socialPlatform(e.getSocialPlatform())
                        .url(e.getUrl())
                        .build())
                .collect(Collectors.toList());
    }

    private String generateSlug(String name) {
        String base= name.toLowerCase()
                .replaceAll("[^a-z0-9\\s]", "")
                .trim().replaceAll("[\\s-]","-");
        if (!companyRepository.existsBySlug(base)){
            return base;
        }
        int counter=1;
        while(companyRepository.existsBySlug(base+"-"+counter)){
            counter++;
        }
        return base+"-"+counter;
    }

    @Override
    public CompanyResponse getCompanyById(Long id) {
        Company company= companyRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Company not Found with id " +id));
        return CompanyMapper.toResponse(company);
    }

    @Override
    public CompanyResponse getMyCompany(Long ownerId) {
        Company company=companyRepository.findByOwnerId(ownerId)
                .orElseThrow(()-> new RuntimeException("Company not exists by ownerId  "));
        return CompanyMapper.toResponse(company);
    }

    @Override
    public List<CompanyResponse> getAllCompanies(CompanyType companyType,
                                                 IndustryType industryType,
                                                 CompanyStatus companyStatus) {
        return companyRepository.findByFilters(companyType,industryType,companyStatus)
                .stream().map(CompanyMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyResponse updateCompany(Long companyId, Long ownerId, CompanyRequest req) {
        Company company= getCompanyEntityById(companyId);

        if(!company.getName().equals(req.getName()) && companyRepository.existsByName(req.getName()) ){
            throw  new RuntimeException("Company already exists. Please choose a different name...!");
        }

        if(req.getRegisterNumber()!=null
                && !req.getRegisterNumber().equals(company.getRegisterNumber())
                && companyRepository.existsByRegisterNumber(req.getRegisterNumber())){
            throw  new RuntimeException("Company already exists. Please choose a different registration Number..!");
        }

        company.setName(req.getName());
        company.setTagLine(req.getTagLine());
        company.setDescription(req.getDescription());
        company.setLogoUrl(req.getLogoUrl());
        company.setWebsite(req.getWebsite());
        company.setEmail(req.getEmail());
        company.setPhone(req.getPhone());
        company.setFoundedYear(req.getFoundedYear());
        company.setCompanySize(req.getCompanySize());
        company.setCompanyType(req.getCompanyType());
        company.setIndustryType(req.getIndustryType());
        company.setRegisterNumber(req.getRegisterNumber());
        company.setSocialLinks(mapSocialLinks(req.getSocialLinks()));

        return CompanyMapper.toResponse(companyRepository.save(company));
    }

    @Override
    public CompanyResponse verifyCompany(Long companyId) {
        Company company=getCompanyEntityById(companyId);
        company.setCompanyStatus(CompanyStatus.ACTIVE);
        company.setIsVerified(true);
        return CompanyMapper.toResponse(companyRepository.save(company));
    }

    @Override
    public void deleteCompany(Long id, Long ownerId) {
        Company company=getCompanyEntityById(id);
        assertOwner(company,ownerId);
        companyRepository.delete(company);

    }

    private void assertOwner(Company company, Long ownerId) {
        if(!company.getOwnerId().equals(ownerId)){
            throw new RuntimeException("You are not the owner of this company...!");
        }
    }

    @Override
    public CompanyResponse deactivateCompany(Long companyId) {
        Company company=getCompanyEntityById(companyId);
        company.setCompanyStatus(CompanyStatus.SUSPENDED);
        company.setIsVerified(false);
        return CompanyMapper.toResponse(company);

    }

    @Override
    public Company getCompanyEntityById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Company not Found with id " +id));

    }
}
