package com.jwland.jwland.domain.exam.repository;

import com.jwland.jwland.entity.ExamOrganization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExamOrganizationRepository extends JpaRepository<ExamOrganization, Long> {
    Optional<ExamOrganization> findByName(String name);

    List<ExamOrganization> findAllByOrderBySeq();

}
