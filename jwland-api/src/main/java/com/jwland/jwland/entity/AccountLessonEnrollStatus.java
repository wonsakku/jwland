package com.jwland.jwland.entity;

import com.jwland.jwland.entity.status.EnrollStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(schema = Constant.SCHEMA_JWLAND)
@Entity
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

    @Enumerated(EnumType.STRING)
    private EnrollStatus enrollStatus;

}
