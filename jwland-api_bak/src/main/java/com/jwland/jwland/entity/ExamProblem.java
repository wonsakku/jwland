package com.jwland.jwland.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(schema = Constant.SCHEMA_JWLAND, name = "exam_problem")
public class ExamProblem {

    @Id @GeneratedValue
    @Column(name = "exam_problem_id")
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "exam_subject_id")
    private ExamSubject examSubject;

    @Column(nullable = false, name = "problem_number")
    private Integer problemNumber;

    @Column(nullable = false, name = "score")
    private Integer score;

    @OneToOne
    @JoinColumn(name = "subject_problem_type_id")
    private SubjectProblemType subjectProblemType;

    @Column(name = "correct_answer_rate")
    private Integer correctAnswerRate;

    private ExamProblem(ExamSubject examSubject, Integer problemNumber, SubjectProblemType subjectProblemType, Integer score, Integer correctAnswerRate) {
        this.examSubject = examSubject;
        this.problemNumber = problemNumber;
        this.subjectProblemType = subjectProblemType;
        this.score = score;
        this.correctAnswerRate = correctAnswerRate;
    }

    public static ExamProblem toEntity(ExamSubject examSubject, Integer problemNumber, SubjectProblemType subjectProblemType, Integer score, Integer correctAnswerRate){
        return new ExamProblem(examSubject, problemNumber, subjectProblemType, score, correctAnswerRate);
    }

    public void update(ExamProblem updating) {
        this.examSubject = updating.examSubject;
        this.problemNumber = updating.problemNumber;
        this.score = updating.score;
        this.subjectProblemType = updating.subjectProblemType;
        this.correctAnswerRate =updating.correctAnswerRate;
    }
}
