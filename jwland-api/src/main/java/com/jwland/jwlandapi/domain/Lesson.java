package com.jwland.jwlandapi.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(schema = Constant.SCHEMA_JWLAND)
public class Lesson extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "classes_id")
    private Long id;

    @Column(nullable = false)
    private String lessonName;

    private String lessonTypeCode;

    @Column(length = 8)
    private String startDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LessonStatus lessonStatus;

}
