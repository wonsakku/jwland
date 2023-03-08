package com.jwland.jwlandapi.domain;

import javax.persistence.*;

@Entity
@Table(schema = Constant.SCHEMA_JWLAND)
public class AccountLessonEnrollStatus extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "account_class_eroll_status_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    private EnrollStatus enrollStatus;

}
