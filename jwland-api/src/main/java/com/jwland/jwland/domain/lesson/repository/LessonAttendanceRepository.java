package com.jwland.jwland.domain.lesson.repository;

import com.jwland.jwland.entity.LessonAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonAttendanceRepository extends JpaRepository<LessonAttendance, Long> {
}
