package com.ktsr.job.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PersonalInfoResponse implements Serializable {

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
