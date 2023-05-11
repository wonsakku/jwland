package com.jwland.jwland.domain.subject.repository;

import com.jwland.jwland.entity.Subject;
import com.jwland.jwland.entity.SubjectProblemType;
import com.jwland.jwland.entity.status.ProblemClassification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectProblemTypeRepository extends JpaRepository<SubjectProblemType, Long> {
    List<SubjectProblemType> findByParentIdAndSubjectIdAndProblemClassificationOrderByOrderSequenceAsc(Long parentId, Long subjectId, ProblemClassification problemClassification);

    List<SubjectProblemType> findBySubjectAndProblemClassificationOrderByOrderSequenceAsc(Subject subject, ProblemClassification problemClassification);

    List<SubjectProblemType> findAllBySubjectIdAndProblemClassification(Long subjectId, ProblemClassification problemClassification);
}
