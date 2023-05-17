package com.jwland.jwland.domain.lesson.repository;

import com.jwland.jwland.entity.LessonAttendance;
import com.jwland.jwland.entity.LessonAttendanceDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LessonAttendanceRepository extends JpaRepository<LessonAttendance, Long> {

    @Query("SELECT la FROM LessonAttendance la " +
            " JOIN FETCH la.account a " +
            " WHERE la.lessonAttendanceDate.id = :lessonAttendanceDateId " +
            " ORDER BY a.name")
    List<LessonAttendance> findAttendanceWithAccountById(Long lessonAttendanceDateId);

    List<LessonAttendance> findByLessonAttendanceDate(LessonAttendanceDate lessonAttendanceDate);
}
