package com.jwland.jwland.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(schema = Constant.SCHEMA_JWLAND,
        uniqueConstraints = @UniqueConstraint(
                name = "lesson_attendance_date_unique",
                columnNames = {"lesson_id", "start_date"}
))
@Entity
public class LessonAttendanceDate extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "lesson_attendance_date_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @Column(nullable = false, length = 8, name = "start_date")
    private String startDate;

    public LessonAttendanceDate(Lesson lesson, String startDate){
        this.lesson = lesson;
        this.startDate = startDate;
    }

}
