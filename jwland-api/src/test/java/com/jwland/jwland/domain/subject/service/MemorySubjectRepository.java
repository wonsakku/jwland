package com.jwland.jwland.domain.subject.service;

import com.jwland.jwland.domain.subject.repository.SubjectRepository;
import com.jwland.jwland.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.*;
import java.util.stream.Collectors;

public class MemorySubjectRepository implements SubjectRepository {

    private final Map<Long, Subject> subjects = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public Subject save(Subject subject) {
        sequence++;
        subject.assignId(sequence);
        subjects.put(subject.getId(), subject);
        return subject;
    }


    @Override
    public Optional<Subject> findById(Long subjectId) {
        final Subject foundSubject = subjects.get(subjectId);
        if(foundSubject != null){
            return Optional.of(foundSubject);
        }
        return Optional.empty();
    }

    @Override
    public List<Subject> findAll() {
        final Set<Long> keys = subjects.keySet();
        return keys.stream()
                .map(key -> subjects.get(key))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Subject targetSubject) {
        subjects.remove(targetSubject.getId());
    }



    @Override
    public List<Subject> findSubjectsUnenrolledInExam(Long examId) {
        return null;
    }

    @Override
    public Subject findByName(String subjectName) {
        return null;
    }

    @Override
    public List<Subject> findActivatedSubjects() {
        return null;
    }

    @Override
    public List<Subject> findSubjectsByUseYnOrderByName(String useYn) {
        return null;
    }

    @Override
    public Page<Subject> findSubjectsByUseYn(String useYn, PageRequest pageRequest) {
        return null;
    }
}
