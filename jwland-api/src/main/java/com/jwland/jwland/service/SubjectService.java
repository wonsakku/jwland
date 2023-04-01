package com.jwland.jwland.service;

import com.jwland.jwland.dto.subject.SubjectDto;
import com.jwland.jwland.entity.Subject;
import com.jwland.jwland.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;


    @Transactional
    public Long enrollSubject(SubjectDto subjectDto) {
        Subject subject = subjectDto.toEnrollDto();
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
}
