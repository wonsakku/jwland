package com.jwland.jwlandapi.domain;

import javax.persistence.*;


@Table(schema = Constant.SCHEMA_JWLAND,
        uniqueConstraints = @UniqueConstraint(
        name = "lesson_attendance_unique",
        columnNames = {"account_id", "lesson_id", "lesson_date"}
))
@Entity
public class LessonAttendance extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "lesson_attendance_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @Column(length = 8, name = "lesson_date")
    private String lessonDate;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus attendanceStatus;
}
