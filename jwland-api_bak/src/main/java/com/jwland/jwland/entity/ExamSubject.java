package com.jwland.jwland.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(schema = Constant.SCHEMA_JWLAND, name = "exam_subject")
public class ExamSubject  extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "exam_subject_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @Column(nullable = false, name = "problem_count")
    private Integer problemCount;

    @Column(nullable = false, name = "perfect_score")
    private Integer perfectScore;

    @Column(name = "problem_paper_url")
    private String problemPaperUrl;

    private ExamSubject(Exam exam, Subject subject, Integer problemCount, Integer perfectScore, String problemPaperUrl) {
        this.exam = exam;
        this.subject = subject;
        this.problemCount = problemCount;
        this.perfectScore = perfectScore;
        this.problemPaperUrl = problemPaperUrl;
    }

    public ExamSubject(Integer problemCount, Integer perfectScore, String problemPaperUrl) {
        this.problemCount = problemCount;
        this.perfectScore = perfectScore;
        this.problemPaperUrl = problemPaperUrl;
    }

    public void update(ExamSubject updating){
        this.problemCount = updating.problemCount;
        this.perfectScore = updating.perfectScore;
        this.problemPaperUrl = updating.problemPaperUrl;
    }

    public static ExamSubject toInsertEntity(Exam exam, Subject subject, Integer problemCount, Integer perfectScore, String problemPaperUrl) {
        return new ExamSubject(exam, subject, problemCount, perfectScore, problemPaperUrl);
    }

    public static ExamSubject toUpdateEntity(Integer problemCount, Integer perfectScore, String problemPaperUrl) {
        return new ExamSubject(problemCount, perfectScore, problemPaperUrl);
    }
}




