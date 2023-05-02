package com.jwland.jwland.entity;

import com.jwland.jwland.entity.status.AttendanceStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(schema = Constant.SCHEMA_JWLAND,
        uniqueConstraints = @UniqueConstraint(
        name = "lesson_attendance_unique",
        columnNames = {"account_id", "lesson_attendance_date_id"}
))
@Entity
public class LessonAttendance extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "lesson_attendance_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "lesson_attendance_date_id")
    private LessonAttendanceDate lessonAttendanceDate;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus attendanceStatus;

    public LessonAttendance(Account account, LessonAttendanceDate lessonAttendanceDate, AttendanceStatus attendanceStatus){
        this.account = account;
        this.lessonAttendanceDate = lessonAttendanceDate;
        this.attendanceStatus = attendanceStatus;
    }

    public void update(LessonAttendance updating){
        this.account = updating.getAccount();
        this.lessonAttendanceDate = updating.getLessonAttendanceDate();
        this.attendanceStatus = updating.getAttendanceStatus();
    }


}
