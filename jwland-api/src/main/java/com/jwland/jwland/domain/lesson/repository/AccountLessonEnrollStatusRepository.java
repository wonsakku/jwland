package com.jwland.jwland.domain.lesson.repository;

import com.jwland.jwland.entity.AccountLessonEnrollStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountLessonEnrollStatusRepository extends JpaRepository<AccountLessonEnrollStatus, Long> {

    @Query("SELECT ales FROM AccountLessonEnrollStatus ales JOIN FETCH ales.account WHERE ales.lesson.id = :lessonId")
    List<AccountLessonEnrollStatus> findByLessonId(@Param("lessonId") Long lessonId);
}
