package com.jwland.jwland.domain;

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
