package com.jwland.jwland.domain.exam.dto;

import com.jwland.jwland.entity.ExamProblem;
import com.jwland.jwland.entity.ExamSubject;
import com.jwland.jwland.entity.SubjectProblemType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ExamProblemSubDto {
    private Long examProblemId;

    @NotNull
    private Integer problemNumber;

    @NotNull
    private Long subjectProblemTypeId;

    @NotNull
    private Integer score;

    private Integer correctAnswerRate;

    public ExamProblem toEntity(ExamSubject examSubject, SubjectProblemType subjectProblemType) {
        return ExamProblem.toEntity(examSubject, this.problemNumber, subjectProblemType, this.score, this.correctAnswerRate);
    }

}
