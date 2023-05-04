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

    @OneToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @Column(nullable = false, name = "perfect_score")
    private Integer perfectScore;

    @Column(name = "problem_paper_url")
    private String problemPaperUrl;

}




