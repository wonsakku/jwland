package com.jwland.jwland.domain.subject.service;

import com.jwland.jwland.domain.subject.dto.SubjectProblemTypeDto;
import com.jwland.jwland.domain.subject.dto.SubjectProblemTypeListDto;
import com.jwland.jwland.entity.Subject;

import java.util.List;

public interface SubjectProblemTypeService {
    Long enrollProblemType(Subject subject, SubjectProblemTypeDto subjectProblemTypeDto);
    List<SubjectProblemTypeListDto> getProblemTypes(Long parentId, String strProblemClassification, Long subjectId);
    void updateProblemType(SubjectProblemTypeDto subjectProblemTypeDto);

}
