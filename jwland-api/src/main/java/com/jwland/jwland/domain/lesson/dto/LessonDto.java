package com.jwland.jwland.domain.lesson.dto;

import com.jwland.jwland.constant.Time;
import com.jwland.jwland.entity.Lesson;
import com.jwland.jwland.entity.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@NoArgsConstructor
@Getter
@Setter
public class LessonDto {

    private Long id;

    @NotNull(message = "lessonName 은 필수 입력값입니다.")
    private String lessonName;

    @NotNull(message = "lessonName 은 필수 입력값입니다.")
    private String schoolClassificationName;

    @NotNull(message = "targetGradeCode 는 필수 입력값입니다.")
    private String targetGradeName;
    @NotNull(message = "subjectId 는 필수 입력값입니다.")
    private Long subjectId;
    @NotNull(message = "startDate 는 필수 입력값입니다.")
    @Pattern(regexp = Time.RegExp.YYYYMMDD, message = "startDate 패턴은 YYYYMMDD 입니다.")
    private String startDate;

    @NotNull(message = "lessonStatusCode 는 필수 입력값입니다.")
    private String lessonStatusCode;

    public LessonDto(String lessonName, String schoolClassificationName, String targetGradeCode, Long subjectId, String startDate, String lessonStatusCode) {
        this.lessonName = lessonName;
        this.schoolClassificationName = schoolClassificationName;
        this.targetGradeName = targetGradeCode;
        this.subjectId = subjectId;
        this.startDate = startDate;
        this.lessonStatusCode = lessonStatusCode;
    }


    public LessonDto(Long id, String lessonName, String schoolClassificationName, String targetGradeName, Long subjectId, String startDate, String lessonStatusCode) {
        this.id = id;
        this.lessonName = lessonName;
        this.schoolClassificationName = schoolClassificationName;
        this.targetGradeName = targetGradeName;
        this.subjectId = subjectId;
        this.startDate = startDate;
        this.lessonStatusCode = lessonStatusCode;
    }


    public Lesson toInsertEntity(Subject subject){
        return Lesson.toInsertEntity(this.lessonName, this.schoolClassificationName, this.targetGradeName, subject, this.startDate, this.lessonStatusCode);
    }


    public Lesson toUpdateEntity(Subject subject) {
        return Lesson.toUpdateEntity(this.id, this.lessonName, this.targetGradeName, subject, this.startDate, this.lessonStatusCode);
    }
}
