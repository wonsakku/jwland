package com.jwland.jwland.domain.subject.repository;

import com.jwland.jwland.entity.Subject;
import com.jwland.jwland.entity.SubjectProblemType;
import com.jwland.jwland.entity.status.ProblemClassification;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface SubjectProblemTypeRepository extends Repository<SubjectProblemType, Long> {
    List<SubjectProblemType> findByParentIdAndSubjectIdAndProblemClassificationOrderByOrderSequenceAsc(Long parentId, Long subjectId, ProblemClassification problemClassification);

    List<SubjectProblemType> findBySubjectAndProblemClassificationOrderByOrderSequenceAsc(Subject subject, ProblemClassification problemClassification);

    List<SubjectProblemType> findAllBySubjectIdAndProblemClassification(Long subjectId, ProblemClassification problemClassification);

    Optional<SubjectProblemType> findById(Long subjectProblemTypeId);

    SubjectProblemType save(SubjectProblemType subjectProblemType);
}
