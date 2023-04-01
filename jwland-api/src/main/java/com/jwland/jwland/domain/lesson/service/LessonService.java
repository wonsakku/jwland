package com.jwland.jwland.domain.lesson.service;

import com.jwland.jwland.domain.lesson.dto.LessonDetailDto;
import com.jwland.jwland.domain.lesson.dto.LessonDto;
import com.jwland.jwland.domain.lesson.dto.LessonsDto;
import com.jwland.jwland.entity.Lesson;
import com.jwland.jwland.entity.Subject;
import com.jwland.jwland.domain.lesson.repository.LessonRepository;
import com.jwland.jwland.domain.subject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class LessonService {

    private final LessonRepository lessonRepository;
    private final SubjectRepository subjectRepository;

    @Transactional
    public Long enrollLesson(LessonDto lessonDto) {
        Long subjectId = lessonDto.getSubjectId();
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 subject 가 없습니다."));

        Lesson lesson = lessonDto.toInsertEntity(subject);
        Lesson saved = lessonRepository.save(lesson);
        return saved.getId();
    }


    @Transactional
    public Long updateLesson(LessonDto lessonDto) {
        Long subjectId = lessonDto.getSubjectId();
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 subject 가 존재하지 않습니다."));

        Lesson updating = lessonDto.toUpdateEntity(subject);
        Lesson target = lessonRepository.findById(updating.getId())
                .orElseThrow(() -> new IllegalArgumentException("일치하는 lesson 이 존재하지 않습니다."));

        target.changeData(updating);

        return target.getId();
    }

    public List<LessonsDto> getLessons() {
        List<Lesson> lessons = lessonRepository.findAll();
        return lessons.stream()
                .map(LessonsDto::new)
                .sorted(Comparator.comparing(LessonsDto::getId).reversed())
                .collect(Collectors.toList())
                ;
    }

    public LessonDetailDto getLessonDetail(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 lessonId 입니다.."));
        return new LessonDetailDto(lesson);
    }

    @Transactional
    public void deleteLesson(Long lessonId) {
        Lesson targetLesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 lessonId 입니다."));

        if(targetLesson.isAfterOpen()){
            throw new IllegalStateException("수업 삭제는 개강 전에만 가능합니다.");
        }

        lessonRepository.delete(targetLesson);
    }
}
