package com.jwland.jwland.domain.lesson.repository;

import com.jwland.jwland.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
