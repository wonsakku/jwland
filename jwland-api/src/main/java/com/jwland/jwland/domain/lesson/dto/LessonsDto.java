package com.jwland.jwland.domain.lesson.dto;

import com.jwland.jwland.entity.Lesson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonsDto {

    private Long id;
    private String lessonName;
    private String targetGrade;
    private String subjectName;
    private String startDate;
    private String lessonStatus;

    public LessonsDto(Lesson lesson){
        this.id = lesson.getId();
        this.lessonName = lesson.getLessonName();
        this.targetGrade = lesson.getGrade();
        this.subjectName = lesson.getSubjectName();
        this.startDate = lesson.getStartDate();
    }

}
