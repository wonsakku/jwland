package com.jwland.jwland.domain.lesson.dto;

import com.jwland.jwland.entity.Lesson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonDetailDto {

    private Long id;
    private String lessonName;
    private String targetGradeCode;
    private Long subjectId;
    private String startDate;
    private String lessonStatusCode;

    public LessonDetailDto(Lesson lesson){
        this.id = lesson.getId();
        this.lessonName = lesson.getLessonName();
        this.targetGradeCode = lesson.getTargetGrade().getGrade();
        this.subjectId = lesson.getSubject().getId();
    }


}














