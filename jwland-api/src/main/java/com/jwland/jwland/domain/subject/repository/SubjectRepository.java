package com.jwland.jwland.domain.subject.repository;

import com.jwland.jwland.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Subject findByName(String subjectName);
}
