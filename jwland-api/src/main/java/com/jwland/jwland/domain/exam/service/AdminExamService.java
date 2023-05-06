package com.jwland.jwland.domain.exam.service;

import com.jwland.jwland.domain.exam.dto.ExamDto;
import com.jwland.jwland.domain.exam.dto.ExamsDto;
import com.jwland.jwland.domain.exam.repository.ExamOrganizationRepository;
import com.jwland.jwland.domain.exam.repository.ExamRepository;
import com.jwland.jwland.entity.Exam;
import com.jwland.jwland.entity.ExamOrganization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
@Service
public class AdminExamService {

    private final ExamCommonService examCommonService;
    private final ExamOrganizationCommonService examOrganizationCommonService;
    private final ExamRepository examRepository;

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

}
