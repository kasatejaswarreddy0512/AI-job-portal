package com.ktsr.job.dto;

import com.ktsr.job.domain.LanguageProficiency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LanguageResponse implements Serializable {

    private Long id;
    private String languageName;
    private LanguageProficiency proficiency;
    private Integer displayOrders;
}
