package com.jwland.jwland.domain.exam.repository;

import com.jwland.jwland.entity.Exam;
import com.jwland.jwland.entity.ExamSubject;
import com.jwland.jwland.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ExamSubjectRepository extends JpaRepository<ExamSubject, Long>, ExamSubjectQueryRepository {
    Optional<ExamSubject> findByExamAndSubject(Exam exam, Subject subject);

    @Query("SELECT es FROM ExamSubject es JOIN FETCH es.subject s WHERE es.id = :examSubjectId")
    Optional<ExamSubject> findByIdFetchJoinSubject(@Param("examSubjectId") Long examSubjectId);
}
