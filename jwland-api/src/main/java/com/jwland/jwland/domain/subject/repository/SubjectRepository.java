package com.jwland.jwland.domain.subject.repository;

import com.jwland.jwland.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long>, SubjectQueryRepository {

    Subject findByName(String subjectName);

    @Query(value = "SELECT s FROM Subject s WHERE s.useYn = 'Y'")
    List<Subject> findActivatedSubjects();
}
