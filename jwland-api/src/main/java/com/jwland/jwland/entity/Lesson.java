package com.jwland.jwland.entity;

import com.jwland.jwland.entity.status.Grade;
import com.jwland.jwland.entity.status.LessonStatus;
import com.jwland.jwland.entity.status.LessonType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(schema = Constant.SCHEMA_JWLAND)
@Entity
public class Lesson extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "lesson_id")
    private Long id;

    @Column(nullable = false)
    private String lessonName;

    private Grade targetGrade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column(length = 8)
    private String startDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LessonStatus lessonStatus;

}
