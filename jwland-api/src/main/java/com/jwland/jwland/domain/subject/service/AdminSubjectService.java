package com.jwland.jwland.domain.subject.service;

import com.jwland.jwland.domain.subject.dto.SubjectDto;
import com.jwland.jwland.domain.subject.dto.SubjectsDto;
import com.jwland.jwland.entity.Subject;

import java.util.List;

public interface AdminSubjectService {
    Long enrollSubject(SubjectDto subjectDto);

    Long updateSubject(SubjectDto subjectDto);

    List<SubjectsDto> findSubjects();

    Subject findSubjectById(Long subjectId);
    void deleteSubject(Long subjectId);
}
