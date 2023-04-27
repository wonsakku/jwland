package com.jwland.jwland.domain.lesson.repository;

import com.jwland.jwland.entity.Lesson;
import com.jwland.jwland.entity.LessonAttendanceDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LessonAttendanceDateRepository extends JpaRepository<LessonAttendanceDate, Long> {
    Optional<LessonAttendanceDate> findTop1ByLessonAndStartDate(Lesson lesson, String todayDateFormat);
}
