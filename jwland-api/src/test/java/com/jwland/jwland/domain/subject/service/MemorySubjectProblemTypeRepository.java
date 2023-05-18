package com.jwland.jwland.domain.subject.service;

import com.jwland.jwland.domain.subject.repository.SubjectProblemTypeRepository;
import com.jwland.jwland.entity.Subject;
import com.jwland.jwland.entity.SubjectProblemType;
import com.jwland.jwland.entity.status.ProblemClassification;

import java.util.*;

public class MemorySubjectProblemTypeRepository implements SubjectProblemTypeRepository {

    private final Map<Long, SubjectProblemType> subjectProblemTypes = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public Optional<SubjectProblemType> findById(Long subjectProblemTypeId) {
        final SubjectProblemType findById = subjectProblemTypes.get(subjectProblemTypeId);
        return findById != null ?
                Optional.of(findById) :
                Optional.empty();
    }

    @Override
    public SubjectProblemType save(SubjectProblemType subjectProblemType) {
        sequence++;
        subjectProblemType.assignId(sequence);
        subjectProblemTypes.put(subjectProblemType.getId(), subjectProblemType);
        return subjectProblemType;
    }

    @Override
    public List<SubjectProblemType> findByParentIdAndSubjectIdAndProblemClassificationOrderByOrderSequenceAsc(Long parentId, Long subjectId, ProblemClassification problemClassification) {
        return null;
    }

    @Override
    public List<SubjectProblemType> findBySubjectAndProblemClassificationOrderByOrderSequenceAsc(Subject subject, ProblemClassification problemClassification) {
        return null;
    }

    @Override
    public List<SubjectProblemType> findAllBySubjectIdAndProblemClassification(Long subjectId, ProblemClassification problemClassification) {
        return null;
    }
}
