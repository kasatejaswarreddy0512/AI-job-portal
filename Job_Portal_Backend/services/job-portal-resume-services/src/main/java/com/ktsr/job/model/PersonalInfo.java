package com.ktsr.job.model;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class PersonalInfo {

    private String firstName;
    private String lastName;

    private String headLine;

    private String email;
    private String phoneNumber;
    private String city;
    private String country;

    private String linkedinUrl;
    private String githubUrl;
    private String portfolioUrl;
    private String websiteUrl;

}
