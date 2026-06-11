package com.ktsr.repository;

import com.ktsr.job.domain.CompanyStatus;
import com.ktsr.job.domain.CompanyType;
import com.ktsr.job.domain.IndustryType;
import com.ktsr.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByOwnerId(Long ownerId);
    boolean existsByOwnerId(Long ownerId);
    boolean existsByName(String name);
    boolean existsBySlug(String slug);
    boolean existsByRegisterNumber(String registerNumber);

    @Query("""
    SELECT c FROM Company c
    WHERE (:companyType IS NULL OR c.companyType = :companyType)
      AND (:industryType IS NULL OR c.industryType = :industryType)
      AND (:companyStatus IS NULL OR c.companyStatus = :companyStatus)
""")
    List<Company> findByFilters(
            @Param("companyType") CompanyType companyType,
            @Param("industryType") IndustryType industryType,
            @Param("companyStatus") CompanyStatus companyStatus
    );

}
