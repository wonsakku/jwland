package com.jwland.jwland.domain.subject.dto;

import com.jwland.jwland.entity.Subject;
import com.jwland.jwland.entity.SubjectProblemType;
import com.jwland.jwland.entity.status.ProblemClassification;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubjectProblemTypeDto {

    private Long problemTypeId;
    private Long subjectId;
    private String problemTypeName;
    private Integer orderSequence;
    private Long parentId;
    private String useYn;
    private String problemClassification;

    public SubjectProblemTypeDto(Long subjectId, String problemTypeName, Integer orderSequence, Long parentId, String useYn, String problemClassification) {
        this.subjectId = subjectId;
        this.problemTypeName = problemTypeName;
        this.orderSequence = orderSequence;
        this.parentId = parentId;
        this.useYn = useYn;
        this.problemClassification = problemClassification;
    }

    public SubjectProblemTypeDto(Long problemTypeId, String problemTypeName, int orderSequence, String useYn){
        this.problemTypeId = problemTypeId;
        this.problemTypeName = problemTypeName;
        this.orderSequence = orderSequence;
        this.useYn = useYn;
    }

    public SubjectProblemType toInsertEntity(Subject subject, SubjectProblemType parent) {
        return SubjectProblemType.toInsertEntity(subject, parent, ProblemClassification.findByName(this.problemClassification), this.problemTypeName, this.orderSequence, useYn);
    }

    public SubjectProblemType toUpdateEntity() {
        return SubjectProblemType.toUpdateEntity(this.problemTypeName, this.orderSequence, useYn);
    }
}
