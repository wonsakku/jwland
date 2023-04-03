package com.jwland.jwland.domain.lesson.dto;

import com.jwland.jwland.entity.Subject;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LessonSubjectsDto {

    private Long subjectId;
    private String name;

    public LessonSubjectsDto(Subject subject){
        this.subjectId = subject.getId();
        this.name = subject.getName();
    }

}
