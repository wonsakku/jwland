package com.jwland.jwland.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(schema = Constant.SCHEMA_JWLAND, name = "account_exam_subject")
public class AccountExamSubject extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "account_exam_subject_id")
    private Long id;

    @OneToOne
    @JoinColumn(nullable = false, name = "account_id")
    private Account account;

    @OneToOne
    @JoinColumn(nullable = false, name = "exam_subject_id")
    private ExamSubject examSubject;

    private Integer score;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accountExamSubject")
    private List<ExamAccountWrongAnswer> wrongAnswers = new ArrayList<>();

}



