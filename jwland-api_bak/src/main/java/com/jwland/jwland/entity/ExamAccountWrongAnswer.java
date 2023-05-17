package com.jwland.jwland.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(schema = Constant.SCHEMA_JWLAND, name = "exam_account_wrong_answer")
public class ExamAccountWrongAnswer extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "exam_account_wrong_answer_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "account_exam_subject_id")
    private AccountExamSubject accountExamSubject;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_problem_id")
    private ExamProblem examProblem;


}
