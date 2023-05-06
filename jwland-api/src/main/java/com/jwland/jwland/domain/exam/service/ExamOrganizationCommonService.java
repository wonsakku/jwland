package com.jwland.jwland.domain.exam.service;

import com.jwland.jwland.domain.exam.repository.ExamOrganizationRepository;
import com.jwland.jwland.entity.ExamOrganization;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExamOrganizationCommonService {

    private final ExamOrganizationRepository examOrganizationRepository;

    public ExamOrganization findExamOrganizationById(Long examOrganizationId){
        return examOrganizationRepository.findById(examOrganizationId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 시험기관입니다."));
    }

}
