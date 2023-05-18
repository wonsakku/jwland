package com.jwland.jwland.domain.subject.service;

import com.jwland.jwland.domain.subject.dto.SubjectProblemTypeDto;
import com.jwland.jwland.domain.subject.dto.SubjectProblemTypeListDto;
import com.jwland.jwland.domain.subject.repository.SubjectProblemTypeRepository;
import com.jwland.jwland.entity.Subject;
import com.jwland.jwland.entity.SubjectProblemType;
import com.jwland.jwland.entity.status.ProblemClassification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SubjectProblemTypeServiceImpl implements SubjectProblemTypeService{

    private final SubjectProblemTypeRepository subjectProblemTypeRepository;

    @Override
    public Long enrollProblemType(Subject subject, SubjectProblemTypeDto subjectProblemTypeDto) {
        final SubjectProblemType parent = findParentSubjectProblem(subjectProblemTypeDto.getParentId());
        final SubjectProblemType subjectProblemType = subjectProblemTypeDto.toInsertEntity(subject, parent);
        SubjectProblemType enrolled = subjectProblemTypeRepository.save(subjectProblemType);
        return enrolled.getId();
    }

    public List<SubjectProblemTypeListDto> getProblemTypes(Long parentId, String strProblemClassification, Long subjectId) {
        final ProblemClassification problemClassification = ProblemClassification.findByName(strProblemClassification);

        if(!problemClassification.topLevel() && parentId == null){
            throw new IllegalArgumentException("parentId가 존재해야 합니다.");
        }

        List<SubjectProblemType> subjectProblemTypes = subjectProblemTypeRepository
                .findByParentIdAndSubjectIdAndProblemClassificationOrderByOrderSequenceAsc(parentId, subjectId, problemClassification);

        return subjectProblemTypes.stream()
                .map(SubjectProblemTypeListDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateProblemType(SubjectProblemTypeDto subjectProblemTypeDto) {
        final SubjectProblemType enrolled = subjectProblemTypeRepository.findById(subjectProblemTypeDto.getProblemTypeId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 분류입니다."));
        enrolled.update(subjectProblemTypeDto.toUpdateEntity());
    }

    private SubjectProblemType findParentSubjectProblem(Long parentId) {
        return parentId == null ? null :
                subjectProblemTypeRepository.findById(parentId)
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 SubjectProblemType 입니다."));
    }
}
