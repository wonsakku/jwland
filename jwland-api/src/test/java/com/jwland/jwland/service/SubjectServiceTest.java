package com.jwland.jwland.service;

import com.jwland.jwland.dto.subject.SubjectDto;
import com.jwland.jwland.entity.Subject;
import com.jwland.jwland.repository.SubjectRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SubjectServiceTest {

    @Autowired
    SubjectService subjectService;
    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("과목 저장")
    void subjectSaveTest() {

        SubjectDto subjectDto = new SubjectDto("생명과학1");
        Long subjectId = subjectService.enrollSubject(subjectDto);

        Subject findById = subjectRepository.findById(subjectId).orElseThrow();

        assertThat(subjectDto.getName()).isEqualTo(findById.getName());
    }

    @Test
    @DisplayName("과목명 수정")
    void subjectUpdateTest(){

        // given
        Subject enrolled = subjectRepository.save(Subject.toInsertEntity("생명과학"));

        String updatingSubjectName = "생명과학1";
        SubjectDto subjectDto = new SubjectDto(enrolled.getId(), updatingSubjectName, "N");

        // when
        Long updatedSubjectId = subjectService.updateSubject(subjectDto);
        Subject findById = subjectRepository.findById(updatedSubjectId).orElseThrow();

        // then
        assertThat(findById.getName()).isEqualTo(updatingSubjectName);

    }
}



