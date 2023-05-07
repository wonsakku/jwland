package com.jwland.jwland.domain.exam.service;

import com.jwland.jwland.domain.exam.dto.ExamOrganizationDto;
import com.jwland.jwland.domain.exam.repository.ExamOrganizationRepository;
import com.jwland.jwland.domain.exam.repository.ExamRepository;
import com.jwland.jwland.entity.ExamOrganization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminExamOrganizationService {

    private final ExamOrganizationRepository examOrganizationRepository;
    private final ExamRepository examRepository;
    private final ExamOrganizationCommonService examOrganizationCommonService;


    public Long enrollExamOrganization(ExamOrganizationDto examOrganizationDto) {
        examOrganizationRepository.findByName(examOrganizationDto.getName())
                .ifPresent((name) -> {throw new IllegalArgumentException("이미 존재하는 이름입니다.");});

        final ExamOrganization examOrganization = examOrganizationDto.toInsertEntity();
        final ExamOrganization saved = examOrganizationRepository.save(examOrganization);

        return saved.getId();
    }

    public List<ExamOrganizationDto> getExamOrganization() {
        List<ExamOrganization> examOrganizations = examOrganizationRepository.findAllByOrderBySeq();
        return examOrganizations.stream()
                .map(ExamOrganizationDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateExamOrganization(ExamOrganizationDto examOrganizationDto) {
        final ExamOrganization enrolled =examOrganizationCommonService.findExamOrganizationById(examOrganizationDto.getOrganizationId());
        enrolled.update(examOrganizationDto.toUpdateEntity());
    }

    @Transactional
    public void deleteExamOrganization(Long organizationId) {

        final ExamOrganization organization = examOrganizationCommonService.findExamOrganizationById(organizationId);

        examRepository.findFirstExamsByOrganization(organization)
                .ifPresent(exam -> {throw new IllegalArgumentException("시험 기관에 등록된 시험이 존재합니다.\n" +
                        "시험을 먼저 삭제한 후 시험 기관을 삭제해주세요");});

        examOrganizationRepository.delete(organization);
    }
}
