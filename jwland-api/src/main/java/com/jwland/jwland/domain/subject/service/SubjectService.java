package com.jwland.jwland.domain.subject.service;

import com.jwland.jwland.domain.subject.dto.SubjectDto;
import com.jwland.jwland.domain.subject.dto.SubjectsDto;
import com.jwland.jwland.entity.Subject;
import com.jwland.jwland.domain.subject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;


    @Transactional
    public Long enrollSubject(SubjectDto subjectDto) {
        Subject subject = subjectDto.toInsertEntity();
        Subject saved = subjectRepository.save(subject);
        return saved.getId();
    }

    @Transactional
    public Long updateSubject(SubjectDto subjectDto) {
        Subject updating = subjectDto.toUpdateEntity();

        Subject target = subjectRepository.findById(updating.getId())
                .orElseThrow(() -> new IllegalArgumentException("일치하는 subject 가 없습니다."));

        target.changeData(updating);
        return target.getId();
    }


    public List<SubjectsDto> findSubjects() {
        List<Subject> subjects = subjectRepository.findAll();
        return subjects.stream()
                .map(SubjectsDto::new)
                .sorted(Comparator.comparing(SubjectsDto::getId))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteSubject(Long id){
        Subject targetSubject = subjectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 subjectId 입니다."));

        // TODO : subject 를 참조하고 있는 도메인이 있는 경우 예외처리

        subjectRepository.delete(targetSubject);
    }
}

