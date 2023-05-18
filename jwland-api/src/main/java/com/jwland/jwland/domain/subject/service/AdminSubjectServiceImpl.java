package com.jwland.jwland.domain.subject.service;

import com.jwland.jwland.domain.subject.dto.SubjectDto;
import com.jwland.jwland.domain.subject.dto.SubjectsDto;
import com.jwland.jwland.domain.subject.repository.SubjectRepository;
import com.jwland.jwland.entity.Subject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
@Service
public class AdminSubjectServiceImpl implements AdminSubjectService {

    private final SubjectRepository subjectRepository;


    @Transactional
    @Override
    public Long enrollSubject(SubjectDto subjectDto) {
        final Subject enrolled = subjectRepository.save(subjectDto.toInsertEntity());
        return enrolled.getId();
    }

    @Transactional
    @Override
    public Long updateSubject(SubjectDto subjectDto) {
        Subject enrolled = findSubjectById(subjectDto.getId());
        enrolled.changeData( subjectDto.toUpdateEntity() );
        return enrolled.getId();
    }

    @Override
    public List<SubjectsDto> findSubjects() {
        final List<Subject> subjects = subjectRepository.findAll();
        return subjects.stream()
                .map(SubjectsDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteSubject(Long subjectId) {
        final Subject deleteTarget = findSubjectById(subjectId);
        subjectRepository.delete(deleteTarget);
    }

    @Override
    public Subject findSubjectById(Long subjectId) {
        return subjectRepository.findById(subjectId)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 subject 가 없습니다."));
    }


}
