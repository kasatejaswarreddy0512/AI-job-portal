package com.ktsr.model;

import com.ktsr.job.domain.CompanyStatus;
import com.ktsr.job.domain.CompanyType;
import com.ktsr.job.domain.CompanySize;
import com.ktsr.job.domain.IndustryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(unique = true)
    private String slug;

    private String email;

    private String phone;

    private String tagLine;

    private String description;

    private String logoUrl;

    private String coverImageUrl;

    private String website;

    private Integer foundedYear;

    @Enumerated(EnumType.STRING)
    private CompanySize companySize;

    @Enumerated(EnumType.STRING)
    private CompanyType companyType;

    @Enumerated(EnumType.STRING)
    private IndustryType industryType;

    @Enumerated(EnumType.STRING)
    private CompanyStatus companyStatus;

    private Boolean isVerified=false;

    @Column(unique = true)
    private String registerNumber;

    @Column(nullable = false,unique = true)
    private Long ownerId;

    @ElementCollection
    private List<SocialLinks> socialLinks= new ArrayList<>();

    private Boolean active=true;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
