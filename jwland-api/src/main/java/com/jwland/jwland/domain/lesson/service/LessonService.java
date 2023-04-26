package com.jwland.jwland.domain.lesson.service;

import com.jwland.jwland.domain.lesson.dto.LessonDetailDto;
import com.jwland.jwland.domain.lesson.dto.LessonDto;
import com.jwland.jwland.domain.lesson.dto.LessonSubjectsDto;
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



    public List<LessonSubjectsDto> getLessonSubjects() {
        List<Subject> subjects = subjectRepository.findActivatedSubjects();
        return subjects.stream()
                .map(LessonSubjectsDto::new)
                .collect(Collectors.toList());
    }
}
