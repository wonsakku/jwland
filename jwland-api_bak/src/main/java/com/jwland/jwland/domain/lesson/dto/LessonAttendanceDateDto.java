package com.jwland.jwland.domain.lesson.dto;

import com.jwland.jwland.entity.LessonAttendanceDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class LessonAttendanceDateDto {
    private Long lessonAttendanceDateId;
    private String startDate;


    public LessonAttendanceDateDto(LessonAttendanceDate lessonAttendanceDate) {
        this.lessonAttendanceDateId = lessonAttendanceDate.getId();
        this.startDate = lessonAttendanceDate.getStartDate();
    }
}
