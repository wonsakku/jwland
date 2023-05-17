package com.jwland.jwland.domain.exam.service;

import com.jwland.jwland.domain.exam.dto.*;
import com.jwland.jwland.domain.exam.repository.ExamProblemRepository;
import com.jwland.jwland.domain.exam.repository.ExamRepository;
import com.jwland.jwland.domain.exam.repository.ExamSubjectRepository;
import com.jwland.jwland.domain.subject.repository.SubjectProblemTypeRepository;
import com.jwland.jwland.domain.subject.repository.SubjectRepository;
import com.jwland.jwland.entity.*;
import com.jwland.jwland.entity.status.ProblemClassification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
@Service
public class AdminExamService {

    private final ExamCommonService examCommonService;
    private final ExamSubjectCommonService subjectCommonService;
    private final ExamOrganizationCommonService examOrganizationCommonService;
    private final ExamRepository examRepository;
    private final SubjectRepository subjectRepository;
    private final ExamSubjectRepository examSubjectRepository;
    private final SubjectProblemTypeRepository subjectProblemTypeRepository;
    private final ExamProblemRepository examProblemRepository;


    @Transactional
    public Long enrollExam(ExamDto examDto) {

        final ExamOrganization examOrganization = examOrganizationCommonService.findExamOrganizationById(examDto.getOrganizationId());

        examRepository.findByYearAndMonthAndOrganization(examDto.getYear(), examDto.getMonth(), examOrganization)
                .ifPresent(exam -> {throw new IllegalArgumentException("이미 등록된 시험입니다.");});

        final Exam saved = examRepository.save( examDto.toEntity(examOrganization) );

        return saved.getId();
    }

    public Page<ExamsDto> getExams(Pageable pageable) {
        Page<Exam> exams = examRepository.findExamsByOrderByYearDescMonthDesc(pageable);
        return exams.map(ExamsDto::new);
    }

    public ExamDto getExamDetail(Long examId) {
        final Exam exam = examCommonService.findExamById(examId);
        return new ExamDto(exam);
    }

    @Transactional
    public Long updateExam(ExamDto examDto) {
        final Exam exam = examCommonService.findExamById(examDto.getExamId());
        final ExamOrganization examOrganization = examOrganizationCommonService.findExamOrganizationById(examDto.getOrganizationId());

        exam.update(examDto.toEntity(examOrganization));
        return exam.getId();
    }

    @Transactional
    public void deleteExam(Long examId) {
        final Exam exam = examCommonService.findExamById(examId);
        examRepository.delete(exam);
    }

    public List<EnrolledExamSubjectDto> getEnrolledExamSubjects(Long examId) {
        final List<ExamSubject> subjects = examSubjectRepository.findExamSubjectsByExamId(examId);
        return subjects.stream()
                .map(EnrolledExamSubjectDto::new)
                .collect(Collectors.toList());
    }

    public List<UnenrolledExamSubjectDto> getUnEnrolledExamSubjects(Long examId) {
        final List<Subject> subjects = subjectRepository.findSubjectsUnenrolledInExam(examId);
        return subjects.stream()
                .map(UnenrolledExamSubjectDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long enrollExamSubject(ExamSubjectDto examSubjectDto) {
        final Exam exam = examCommonService.findExamById(examSubjectDto.getExamId());
        final Subject subject = subjectRepository.findById(examSubjectDto.getSubjectId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 과목입니다."));

        examSubjectRepository.findByExamAndSubject(exam, subject)
                .ifPresent(examSubject -> {throw new IllegalArgumentException("이미 등록된 시험 과목입니다.");});

        final ExamSubject saved = examSubjectRepository.save(examSubjectDto.toInsertEntity(exam, subject));

        return saved.getId();
    }

    @Transactional
    public Long updateExamSubject(ExamSubjectDto examSubjectDto) {

        final ExamSubject enrolled = examSubjectRepository.findById(examSubjectDto.getExamSubjectId())
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 시험 과목입니다."));

        final ExamSubject updating = examSubjectDto.toUpdateEntity();
        enrolled.update(updating);

        return enrolled.getId();
    }

    @Transactional
    public void deleteExamSubject(Long examSubjectId) {
        final ExamSubject enrolled = examSubjectRepository.findById(examSubjectId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 시험 과목입니다."));

        examSubjectRepository.delete(enrolled);
    }

    public List<ExamSubjectProblemTypeDto> getSubjectProblemTypes(Long examSubjectId, String problemClassification) {
        final ExamSubject examSubject = examSubjectRepository.findByIdFetchJoinSubject(examSubjectId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 시험 과목입니다."));
        List<SubjectProblemType> subjectProblemTypes = subjectProblemTypeRepository
                .findBySubjectAndProblemClassificationOrderByOrderSequenceAsc(examSubject.getSubject(), ProblemClassification.findByName(problemClassification));
        return subjectProblemTypes.stream()
                .map(ExamSubjectProblemTypeDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void enrollExamProblems(ExamProblemListDto examProblemListDto, Long examSubjectId) {
        final ExamSubject examSubject = subjectCommonService.findById(examSubjectId);
        final List<ExamProblem> enrolledExamProblems = examProblemRepository.findAllById(examProblemListDto.getExamProblemIds());
        final List<SubjectProblemType> enrolledProblemTypes = subjectProblemTypeRepository.findAllById(examProblemListDto.getSubjectProblemTypeIds());
        final SubjectProblemTypes enrolledSubjectProblemTypes = new SubjectProblemTypes(enrolledProblemTypes);

        // 기등록 문제 수정
        for (ExamProblem enrolled : enrolledExamProblems) {
            final ExamProblemSubDto subDto = examProblemListDto.getSubDtoByExamProblemId(enrolled.getId());
            final Long subjectProblemTypeId = subDto.getSubjectProblemTypeId();
            final SubjectProblemType subjectProblemType = enrolledSubjectProblemTypes.findById(subjectProblemTypeId);
            ExamProblem updating = subDto.toEntity(examSubject, subjectProblemType);
            enrolled.update(updating);
        }

        // 미등록 문제 등록
        final List<ExamProblemSubDto> subDtos = examProblemListDto.getSubDtosNotHavingExamProblemId();
        for (ExamProblemSubDto subDto : subDtos) {
            final SubjectProblemType subjectProblem = enrolledSubjectProblemTypes.findById(subDto.getSubjectProblemTypeId());
            final ExamProblem examProblem = subDto.toEntity(examSubject, subjectProblem);
            examProblemRepository.save(examProblem);
        }
    }

    public List<ExamProblemDto> getExamProblems(Long examSubjectId) {
        List<ExamProblem> enrolled = examProblemRepository.findSmallClassificationTypesByExamSubjectId(examSubjectId, ProblemClassification.SMALL);
        return enrolled.stream()
                .map(ExamProblemDto::new)
                .collect(Collectors.toList());
    }
}




