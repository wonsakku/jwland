package com.jwland.jwland.domain.subject.service;

import com.jwland.jwland.domain.subject.repository.SubjectRepository;
import com.jwland.jwland.entity.Subject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SubjectCommonService {

    private final SubjectRepository subjectRepository;

    public Subject findById(Long subjectId){
        return subjectRepository.findById(subjectId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 subject 입니다."));
    }
}
