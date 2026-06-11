package com.ktsr.job.dto;

import com.ktsr.job.domain.SocialPlatform;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocialLinkResponse {

    private SocialPlatform socialPlatform;
    private String url;
}
