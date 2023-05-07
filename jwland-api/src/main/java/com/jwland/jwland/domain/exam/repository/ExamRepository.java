package com.jwland.jwland.domain.exam.repository;

import com.jwland.jwland.entity.Exam;
import com.jwland.jwland.entity.ExamOrganization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    Optional<Exam> findByYearAndMonthAndOrganization(Integer year, Integer month, ExamOrganization examOrganization);

    Page<Exam> findExamsByOrderByYearDescMonthDesc(Pageable pageable);

    Optional<Exam> findFirstExamsByOrganization(ExamOrganization organization);
}
