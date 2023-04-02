package com.jwland.jwland.domain.subject;

import com.jwland.jwland.domain.subject.service.SubjectService;
import com.jwland.jwland.domain.subject.dto.SubjectDto;
import com.jwland.jwland.domain.subject.dto.SubjectsDto;
import com.jwland.jwland.entity.Subject;
import com.jwland.jwland.domain.subject.repository.SubjectRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
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

        SubjectDto subjectDto = new SubjectDto("생명과학1","Y");
        Long subjectId = subjectService.enrollSubject(subjectDto);

        Subject findById = subjectRepository.findById(subjectId).orElseThrow();

        assertThat(subjectDto.getName()).isEqualTo(findById.getName());
    }

    @Test
    @DisplayName("과목명 수정")
    void subjectUpdateTest(){

        // given
        Subject enrolled = subjectRepository.save(Subject.toInsertEntity("생명과학", "Y"));

        String updatingSubjectName = "생명과학1";
        SubjectDto subjectDto = new SubjectDto(enrolled.getId(), updatingSubjectName, "N");

        // when
        Long updatedSubjectId = subjectService.updateSubject(subjectDto);
        Subject findById = subjectRepository.findById(updatedSubjectId).orElseThrow();

        // then
        assertThat(findById.getName()).isEqualTo(updatingSubjectName);

    }

    @Test
    @DisplayName("과목 목록 조회")
    void subjectFindAll(){

        // when
        List<SubjectsDto> subjects = subjectService.findSubjects();

        // then
        for (SubjectsDto subject : subjects) {
            assertThat(subject.getId()).isNotNull();
            assertThat(subject.getName()).isNotNull();
            assertThat(subject.getUseYn()).isNotNull();
            assertThat(subject.getModifiedDateTime()).isNotNull();
        }
    }

    @Test
    @DisplayName("과목 삭제 테스트")
    void deleteSubjectTest(){

        Subject subject = subjectRepository.findByName("생명과학1");
        Long subjectId = subject.getId();

        subjectService.deleteSubject(subjectId);

        assertThat(subjectRepository.findById(subjectId).isEmpty()).isTrue();
    }


}



