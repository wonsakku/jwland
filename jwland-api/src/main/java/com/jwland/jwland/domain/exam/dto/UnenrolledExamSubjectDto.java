package com.jwland.jwland.domain.exam.dto;

import com.jwland.jwland.entity.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UnenrolledExamSubjectDto {

    private Long subjectId;
    private String subjectName;

    public UnenrolledExamSubjectDto(Subject subject){
        this.subjectId = subject.getId();
        this.subjectName = subject.getName();
    }

}
