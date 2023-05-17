package com.jwland.jwland.domain.lesson.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jwland.jwland.constant.Time;
import com.jwland.jwland.entity.LessonAttendance;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class LessonAttendanceInfoDto {

    private Long accountId;
    private String schoolName;
    private String grade;
    private String name;

    @JsonFormat(pattern = Time.Pattern.DEFAULT_PATTERN, timezone = Time.Zone.ASIA_SEOUL)
    private LocalDateTime lastModifiedDateTime;
    private String attendanceStatus;

    public LessonAttendanceInfoDto(LessonAttendance lessonAttendance){
        this.accountId = lessonAttendance.getAccount().getId();
        this.schoolName = lessonAttendance.getAccount().getSchoolName();
        this.grade = lessonAttendance.getAccount().getGrade().getGrade();
        this.name = lessonAttendance.getAccount().getName();
        this.lastModifiedDateTime = lessonAttendance.getModifiedDateTime();
        this.attendanceStatus = lessonAttendance.getAttendanceStatus().name();
    }
}
