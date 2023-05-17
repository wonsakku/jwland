package com.jwland.jwland.domain.exam.service;

import com.jwland.jwland.domain.exam.repository.ExamRepository;
import com.jwland.jwland.entity.Exam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExamCommonService {

    private final ExamRepository examRepository;

    public Exam findExamById(Long examId){
        return examRepository.findById(examId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 시험입니다."));
    }

}
