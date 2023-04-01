package com.jwland.jwland.domain.lesson;

import com.jwland.jwland.domain.lesson.dto.LessonDto;
import com.jwland.jwland.entity.Lesson;
import com.jwland.jwland.entity.Subject;
import com.jwland.jwland.entity.status.Grade;
import com.jwland.jwland.entity.status.LessonStatus;
import com.jwland.jwland.domain.lesson.repository.LessonRepository;
import com.jwland.jwland.domain.subject.repository.SubjectRepository;
import com.jwland.jwland.domain.lesson.service.LessonService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest
class LessonServiceTest {

    @Autowired
    LessonService lessonService;

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    SubjectRepository subjectRepository;


    @Test
    @DisplayName("수업 등록 테스트")
    void lessonEnrollTest(){

        // given
        Subject subject = subjectRepository.findByName("생명과학1");
        LessonDto lessonDto = new LessonDto("나의 첫 강의", Grade.HIGH_2.name(), subject.getId(), "20230401", LessonStatus.BEFORE_OPEN.name());

        // when
        Long lessonId = lessonService.enrollLesson(lessonDto);
        Lesson enrolledLesson = lessonRepository.findById(lessonId).orElseThrow();

        // then
        assertThat(lessonDto.getLessonName()).isEqualTo(enrolledLesson.getLessonName());
        assertThat(lessonDto.getSubjectId()).isEqualTo(enrolledLesson.getSubject().getId());
        assertThat(subject.getName()).isEqualTo(enrolledLesson.getSubjectName());
        assertThat(lessonDto.getLessonStatusCode()).isEqualTo(enrolledLesson.getLessonStatus().name());
        assertThat(lessonDto.getStartDate()).isEqualTo(enrolledLesson.getStartDate());
        assertThat(lessonDto.getTargetGradeCode()).isEqualTo(enrolledLesson.getTargetGrade().name());
    }

    @Test
    @DisplayName("수업 수정 테스트")
    void lessonUpdateTest(){

        // given
        Lesson lesson = getTestLesson(LessonStatus.BEFORE_OPEN);
        Lesson saved = lessonRepository.save(lesson);

        Subject updatingSubject = subjectRepository.findByName("화학1");
        LessonDto updatingDto = new LessonDto(saved.getId(), "업데이트 첫 강의", Grade.HIGH_3.name(), updatingSubject.getId(), "20230401", LessonStatus.OPEN.name());

        // when
        Long updatedId = lessonService.updateLesson(updatingDto);
        Lesson updated = lessonRepository.findById(updatedId).orElseThrow();

        assertThat(updatingDto.getLessonName()).isEqualTo(updated.getLessonName());
        assertThat(updatingDto.getTargetGradeCode()).isEqualTo(updated.getTargetGrade().name());
        assertThat(updatingDto.getSubjectId()).isEqualTo(updated.getSubject().getId());
        assertThat(updatingSubject.getName()).isEqualTo(updated.getSubjectName()); // transaction 이 없을 경우 lazyLoading 으로 인한 초기화 에러 발생?
        assertThat(updatingSubject.getName()).isEqualTo(updated.getSubject().getName());
        assertThat(updatingDto.getStartDate()).isEqualTo(updated.getStartDate());
        assertThat(updatingDto.getLessonStatusCode()).isEqualTo(updated.getLessonStatus().name());
    }

    @Test
    @DisplayName("lesson 삭제 테스트")
    void deleteLessonTest(){
        // given
        Lesson lesson = getTestLesson(LessonStatus.BEFORE_OPEN);
        Lesson saved = lessonRepository.save(lesson);

        // when
        lessonService.deleteLesson(saved.getId());

        // then
        assertThat(lessonRepository.findById(saved.getId()).isEmpty()).isTrue();
    }


    @Test
    @DisplayName("개강한 lesson 삭제 시 예외 처리 테스트")
    void deleteOpenedLessonException(){
        Lesson lesson = getTestLesson(LessonStatus.OPEN);
        Lesson saved = lessonRepository.save(lesson);

        assertThatThrownBy(() -> {
            lessonService.deleteLesson(saved.getId());
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage("수업 삭제는 개강 전에만 가능합니다.");
    }


    private Lesson getTestLesson(LessonStatus lessonStatus) {
        Subject subject = subjectRepository.findByName("생명과학1");
        LessonDto lessonDto = new LessonDto("나의 첫 강의", Grade.HIGH_2.name(), subject.getId(), "20230401", lessonStatus.name());
        Lesson lesson = lessonDto.toInsertEntity(subject);
        return lesson;
    }


}


