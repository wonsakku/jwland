package com.jwland.jwland.entity;

import com.jwland.jwland.entity.status.Grade;
import com.jwland.jwland.entity.status.LessonStatus;
import com.jwland.jwland.entity.status.SchoolClassification;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(schema = Constant.SCHEMA_JWLAND)
@Entity
public class Lesson extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "lesson_id")
    private Long id;

    @Column(nullable = false)
    private String lessonName;

    @Enumerated(EnumType.STRING)
    @Column(name = "school_classification")
    private SchoolClassification schoolClassification;

    @Enumerated(EnumType.STRING)
    @Column(name = "target_grade")
    private Grade targetGrade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column(length = 8)
    private String startDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LessonStatus lessonStatus;

    private Lesson(String lessonName, SchoolClassification schoolClassification, Grade targetGrade, Subject subject, String startDate, LessonStatus lessonStatus) {
        this.lessonName = Objects.requireNonNull(lessonName);
        this.schoolClassification = schoolClassification;
        this.targetGrade = targetGrade;
        this.subject = subject;
        this.startDate = Objects.requireNonNull(startDate);
        this.lessonStatus = lessonStatus;
    }

    private Lesson(Long id, String lessonName, Grade targetGrade, Subject subject, String startDate, LessonStatus lessonStatus) {
        this.id = Objects.requireNonNull(id);
        this.lessonName = Objects.requireNonNull(lessonName);
        this.targetGrade = targetGrade;
        this.subject = subject;
        this.startDate = Objects.requireNonNull(startDate);
        this.lessonStatus = lessonStatus;
    }

    public static Lesson toInsertEntity(String lessonName, String schoolClassificationName, String targetGradeName, Subject subject, String startDate, String lessonStatusName) {
        SchoolClassification schoolClassification = SchoolClassification.findByName(schoolClassificationName);
        Grade targetGrade = Grade.findByNumber(targetGradeName);
        LessonStatus lessonStatus = LessonStatus.findByName(lessonStatusName);
        return new Lesson(lessonName, schoolClassification, targetGrade, subject, startDate, lessonStatus);
    }

    public static Lesson toUpdateEntity(Long id, String lessonName, String targetGradeName, Subject subject, String startDate, String lessonStatusName) {
        Grade targetGrade = Grade.findByNumber(targetGradeName);
        LessonStatus lessonStatus = LessonStatus.findByName(lessonStatusName);
        return new Lesson(id, lessonName, targetGrade, subject, startDate, lessonStatus);
    }

    public void changeData(Lesson updating){
        this.lessonName = updating.lessonName;
        this.targetGrade = updating.targetGrade;
        this.subject = updating.subject;
        this.startDate = updating.startDate;
        this.lessonStatus = updating.lessonStatus;
    }

    public String getSubjectName() {
        return this.subject.getName();
    }

    public String getGrade(){
        return this.targetGrade.getGrade();
    }

    public boolean isAfterOpen() {
        return lessonStatus.isAfterOpen();
    }

    public boolean isClosed() {
        return false;
    }
}
