package com.jwland.jwland.domain.exam.dto;

import com.jwland.jwland.entity.Exam;
import com.jwland.jwland.entity.ExamSubject;
import com.jwland.jwland.entity.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ExamSubjectDto {

    private Long examSubjectId;

    @NotNull
    private Long examId;
    @NotNull
    private Long subjectId;
    @NotNull
    private Integer problemCount;
    @NotNull
    private Integer perfectScore;

    private String problemPaperUrl;

    public ExamSubject toInsertEntity(Exam exam, Subject subject) {
        return ExamSubject.toInsertEntity(exam, subject, this.problemCount, this.perfectScore, this.problemPaperUrl);
    }

    public ExamSubject toUpdateEntity() {
        return ExamSubject.toUpdateEntity(this.problemCount, this.perfectScore, this.problemPaperUrl);
    }
}
