package com.jwland.jwland.domain.exam.repository;

import com.jwland.jwland.entity.ExamSubject;

import java.util.List;

public interface ExamSubjectQueryRepository {

    List<ExamSubject> findExamSubjectsByExamId(Long examId);
}
