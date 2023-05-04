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
    private ExamSubject subject;

    @Column(nullable = false, name = "problem_number")
    private Integer problemNumber;

    @Column(nullable = false, name = "score")
    private Integer score;

    @OneToOne
    @JoinColumn(name = "subject_problem_type_id")
    private SubjectProblemType subjectProblemType;

    private String description;
}
