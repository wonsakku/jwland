package com.jwland.jwland.domain.lesson.service;

import com.jwland.jwland.domain.lesson.repository.LessonRepository;
import com.jwland.jwland.entity.Lesson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LessonCommonService {
    private final LessonRepository lessonRepository;

    public Lesson getUnclosedLesson(Long lessonId){
        final Lesson lesson = getLesson(lessonId);
        if(lesson.isClosed()){
            throw new IllegalArgumentException("종료된 lesson 입니다.");
        }
        return lesson;
    }

    public Lesson getLesson(Long lessonId){
        return lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 lessonId 입니다."));
    }
}
