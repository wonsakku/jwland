package com.jwland.jwland.domain.exam.dto;

import com.jwland.jwland.entity.SubjectProblemType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ExamSubjectProblemTypeDto {

    private Long subjectProblemTypeId;
    private Long parentId;
    private String subjectProblemTypeName;

    public ExamSubjectProblemTypeDto(SubjectProblemType subjectProblemType) {
        this.subjectProblemTypeId = subjectProblemType.getId();
        this.parentId = subjectProblemType.getParent() == null ? null : subjectProblemType.getParent().getId();
        this.subjectProblemTypeName = subjectProblemType.getName();
    }
}
