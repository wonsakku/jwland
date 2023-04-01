package com.jwland.jwland.dto.lesson;

import com.jwland.jwland.entity.Lesson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LessonDto {

    private Long id;
    private String lessonName;
    private String lessonTypeCode;
    private String lessonStatusCode;
    private String startDate;

    public LessonDto(String lessonName, String lessonTypeCode, String lessonStatusCode, String startDate) {
        this.lessonName = lessonName;
        this.lessonTypeCode = lessonTypeCode;
        this.lessonStatusCode = lessonStatusCode;
        this.startDate = startDate;
    }

    public Lesson toEntity() {

        if(id == null){
//            return Lesson.toInsertEntity();
        }

        return null;
    }
}
