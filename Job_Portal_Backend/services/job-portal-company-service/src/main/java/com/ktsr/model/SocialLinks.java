package com.ktsr.model;

import com.ktsr.job.domain.SocialPlatform;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Builder
public class SocialLinks {

    @Enumerated(EnumType.STRING)
    private SocialPlatform socialPlatform;
    private String url;
}
