package com.jwland.jwland.domain.subject.repository;

import com.jwland.jwland.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends Repository<Subject, Long>, SubjectQueryRepository {

    Subject findByName(String subjectName);

    @Query(value = "SELECT s FROM Subject s WHERE s.useYn = 'Y'")
    List<Subject> findActivatedSubjects();

    Subject save(Subject subject);

    Optional<Subject> findById(Long subjectId);

    List<Subject> findAll();

    void delete(Subject targetSubject);

    List<Subject> findSubjectsByUseYnOrderByName(String useYn);

    Page<Subject> findSubjectsByUseYn(String useYn, PageRequest pageRequest);
}
