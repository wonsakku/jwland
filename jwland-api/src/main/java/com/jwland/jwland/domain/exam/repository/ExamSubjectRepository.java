package com.jwland.jwland.domain.exam.repository;

import com.jwland.jwland.entity.Exam;
import com.jwland.jwland.entity.ExamSubject;
import com.jwland.jwland.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExamSubjectRepository extends JpaRepository<ExamSubject, Long>, ExamSubjectQueryRepository {
    Optional<ExamSubject> findByExamAndSubject(Exam exam, Subject subject);
}
