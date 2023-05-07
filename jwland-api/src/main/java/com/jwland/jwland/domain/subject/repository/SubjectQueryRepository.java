package com.jwland.jwland.domain.subject.repository;

import com.jwland.jwland.entity.Subject;

import java.util.List;

public interface SubjectQueryRepository {
    List<Subject> findSubjectsUnenrolledInExam(Long examId);

}
