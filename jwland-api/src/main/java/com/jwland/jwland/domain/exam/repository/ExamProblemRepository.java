package com.jwland.jwland.domain.exam.repository;

import com.jwland.jwland.entity.ExamProblem;
import com.jwland.jwland.entity.status.ProblemClassification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExamProblemRepository extends JpaRepository<ExamProblem, Long> {

    @Query("SELECT ep FROM ExamProblem ep " +
            " JOIN FETCH ep.subjectProblemType spt " + // small
            " JOIN FETCH ep.examSubject es " +
            " WHERE es.id = :examSubjectId " +
            " AND spt.problemClassification = :smallClassification " +
            " ORDER BY ep.problemNumber" )
    List<ExamProblem> findSmallClassificationTypesByExamSubjectId(@Param("examSubjectId") Long examSubjectId,
                                                                  @Param("smallClassification") ProblemClassification smallClassification);
}
