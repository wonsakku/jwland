package com.jwland.jwland.domain.exam.dto;

import com.jwland.jwland.entity.ExamProblem;
import lombok.Data;

@Data
public class ExamProblemDto {

    private Long examProblemId;
    private Integer problemNumber;
    private Long largeClassificationId;
    private Long middleClassificationId;
    private Long smallClassificationId;
    private Integer score;
    private Integer correctAnswerRate;

    public ExamProblemDto(ExamProblem examProblem){
        this.examProblemId = examProblem.getId();
        this.problemNumber = examProblem.getProblemNumber();
        this.largeClassificationId = examProblem.getSubjectProblemType().getParent().getParent().getId();
        this.middleClassificationId = examProblem.getSubjectProblemType().getParent().getId();
        this.smallClassificationId = examProblem.getExamSubject().getId();
        this.score = examProblem.getScore();
        this.correctAnswerRate = examProblem.getScore();
    }


}
