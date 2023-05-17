package com.jwland.jwland.domain.exam.service;

import com.jwland.jwland.domain.exam.repository.ExamSubjectRepository;
import com.jwland.jwland.entity.ExamSubject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExamSubjectCommonService {

    private final ExamSubjectRepository examSubjectRepository;

    public ExamSubject findById(Long examSubjectId){
        return examSubjectRepository.findById(examSubjectId)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 시험 과목이 없습니다."));
    }

}
