package com.jwland.jwland.domain.subject.dto;

import com.jwland.jwland.entity.SubjectProblemType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubjectProblemTypeListDto {

    private Long subjectProblemTypeId;
    private String problemTypeName;
    private Integer orderSequence;
    private Long parentId;
    private String useYn;
    private String problemClassification;

    public SubjectProblemTypeListDto(SubjectProblemType subjectProblemType){
        this.subjectProblemTypeId = subjectProblemType.getId();
        this.problemTypeName = subjectProblemType.getName();
        this.orderSequence = subjectProblemType.getOrderSequence();
        this.useYn = subjectProblemType.getUseYn();
        this.problemClassification = subjectProblemType.getProblemClassification().name();
    }

}
