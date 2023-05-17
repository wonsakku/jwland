package com.jwland.jwland.entity;

import com.jwland.jwland.entity.status.EnrollStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(schema = Constant.SCHEMA_JWLAND,
        uniqueConstraints = @UniqueConstraint(name = "account_lesson_enroll_status_unique",
        columnNames = {"account_id", "lesson_id"}))
@Entity
public class AccountLessonEnrollStatus extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_lesson_enroll_status_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @Enumerated(EnumType.STRING)
    private EnrollStatus enrollStatus;

    public AccountLessonEnrollStatus(Account account, Lesson lesson, EnrollStatus enrollStatus) {
        this.account = account;
        this.lesson = lesson;
        this.enrollStatus = enrollStatus;
    }
}
