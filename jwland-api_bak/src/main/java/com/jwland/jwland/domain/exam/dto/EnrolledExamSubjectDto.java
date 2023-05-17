package com.jwland.jwland.domain.exam.dto;

import com.jwland.jwland.entity.ExamSubject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnrolledExamSubjectDto {

    private Long examSubjectId;
    private Long subjectId;
    private Long examId;
    private String subjectName;
    private Integer perfectScore;
    private Integer problemCount;
    private String problemPaperUrl;

    public EnrolledExamSubjectDto(ExamSubject examSubject) {
        this.examSubjectId = examSubject.getId();
        this.examId = examSubject.getSubject().getId();
        this.subjectId = examSubject.getSubject().getId();
        this.subjectName = examSubject.getSubject().getName();
        this.perfectScore = examSubject.getPerfectScore();
        this.problemCount = examSubject.getProblemCount();
        this.problemPaperUrl = examSubject.getProblemPaperUrl();
    }
}
