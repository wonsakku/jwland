package com.jwland.jwland.domain.subject.service;

import com.jwland.jwland.domain.subject.dto.SubjectProblemTypeDto;
import com.jwland.jwland.domain.subject.repository.SubjectProblemTypeRepository;
import com.jwland.jwland.entity.Subject;
import com.jwland.jwland.entity.SubjectProblemType;
import com.jwland.jwland.entity.status.ProblemClassification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SubjectProblemTypeServiceImplDoubleTest {

    SubjectProblemTypeService subjectProblemTypeService;
    private SubjectProblemTypeRepository subjectProblemTypeRepository;

    @BeforeEach
    void init(){
        subjectProblemTypeRepository = new MemorySubjectProblemTypeRepository();
        subjectProblemTypeService = new SubjectProblemTypeServiceImpl(subjectProblemTypeRepository);
    }

    @Test
    @DisplayName("상위 분류가 없는 경우의 문제 유형 등록(대분류 문제 유형 등록)")
    void enroll_notHavingParent_ProblemType_Test(){

        // given
        Subject subject = new Subject(1L, "테스트 과목", "Y");
        final SubjectProblemTypeDto subjectProblemTypeDto
                = new SubjectProblemTypeDto(subject.getId(), "유전", 1, null, "Y", ProblemClassification.LARGE.name());

        // when
        Long enrolledId = subjectProblemTypeService.enrollProblemType(subject, subjectProblemTypeDto);
        SubjectProblemType findById = subjectProblemTypeRepository.findById(enrolledId).get();

        // then
        assertThat(findById.getSubject()).isEqualTo(subject);
        assertThat(findById.getProblemClassification()).isEqualTo(ProblemClassification.LARGE);
        assertThat(findById.getName()).isEqualTo("유전");
        assertThat(findById.getOrderSequence()).isEqualTo(1);
        assertThat(findById.getParent()).isNull();
    }

    @Test
    @DisplayName("상위 분류가 있는 경우의 문제 유형 등록")
    void enroll_HavingParent_ProblemType_Test(){

        // given
        Subject subject = new Subject(1L, "테스트 과목", "Y");
        final SubjectProblemType parent = SubjectProblemType.toInsertEntity(subject, null, ProblemClassification.LARGE, "유전", 1, "Y");
        subjectProblemTypeRepository.save(parent);

        final SubjectProblemTypeDto subjectProblemTypeDto
                = new SubjectProblemTypeDto(subject.getId(), "샤가프의 법칙", 1, parent.getId(), "Y", ProblemClassification.MIDDLE.name());

        // when
        Long enrolledId = subjectProblemTypeService.enrollProblemType(subject, subjectProblemTypeDto);
        SubjectProblemType findById = subjectProblemTypeRepository.findById(enrolledId).get();

        // then
        assertThat(findById.getSubject()).isEqualTo(subject);
        assertThat(findById.getProblemClassification()).isEqualTo(ProblemClassification.MIDDLE);
        assertThat(findById.getName()).isEqualTo("샤가프의 법칙");
        assertThat(findById.getOrderSequence()).isEqualTo(1);
        assertThat(findById.getParent()).isEqualTo(parent);
    }

    @Test
    @DisplayName("문제 유형 업데이트 테스트")
    void update_Test(){

        // given
        Subject subject = new Subject(1L, "테스트 과목", "Y");
        final SubjectProblemType enrolled = SubjectProblemType.toInsertEntity(subject, null, ProblemClassification.LARGE, "유전", 1, "Y");
        subjectProblemTypeRepository.save(enrolled);
        final String updatedName = "update 유전";
        final int updatedOrderSequence = 10;
        final String updatedUseYn = "N";
        final SubjectProblemTypeDto updateDto = new SubjectProblemTypeDto(enrolled.getId(), updatedName, updatedOrderSequence, updatedUseYn);

        // when
        subjectProblemTypeService.updateProblemType(updateDto);
        final SubjectProblemType findById = subjectProblemTypeRepository.findById(enrolled.getId()).get();

        // then
        assertThat(findById.getName()).isEqualTo(updatedName);
        assertThat(findById.getOrderSequence()).isEqualTo(updatedOrderSequence);
        assertThat(findById.getUseYn()).isEqualTo(updatedUseYn);

    }

}