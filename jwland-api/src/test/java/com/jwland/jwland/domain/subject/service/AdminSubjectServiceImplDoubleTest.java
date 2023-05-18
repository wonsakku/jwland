package com.jwland.jwland.domain.subject.service;

import com.jwland.jwland.domain.subject.dto.SubjectDto;
import com.jwland.jwland.domain.subject.dto.SubjectsDto;
import com.jwland.jwland.domain.subject.repository.SubjectRepository;
import com.jwland.jwland.entity.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


class AdminSubjectServiceImplDoubleTest {

    AdminSubjectService adminSubjectService;
    private SubjectRepository subjectRepository;

    @BeforeEach
    void setUp(){
        subjectRepository = new MemorySubjectRepository();
        adminSubjectService = new AdminSubjectServiceImpl(subjectRepository);
    }

    @Test
    void enroll_Subject_Test(){
        // given
        final String useYn = "Y";
        final String subjectName = "생명과학1";
        final SubjectDto subjectDto = new SubjectDto(subjectName, useYn);

        // when
        Long subjectId = adminSubjectService.enrollSubject(subjectDto);
        final Subject enrolled = subjectRepository.findById(subjectId).get();

        // then
        assertThat(enrolled.getName()).isEqualTo(subjectName);
        assertThat(enrolled.getUseYn()).isEqualTo(useYn);
        assertThat(enrolled.getId()).isEqualTo(subjectId);
    }

    @Test
    void update_Subject_Test(){

        // given
        final Subject enrolled = subjectRepository.save(Subject.toInsertEntity("생명과학1", "Y"));
        final String updateName = "생명과학2";
        final String updateYn = "N";
        final SubjectDto updateDto = new SubjectDto(enrolled.getId(), updateName, updateYn);

        // when
        adminSubjectService.updateSubject(updateDto);
        final Subject updated = subjectRepository.findById(enrolled.getId()).get();

        // then
        assertThat(updated.getName()).isEqualTo(updateName);
        assertThat(updated.getUseYn()).isEqualTo(updateYn);
    }



    @Test
    void find_Subject_Test(){
        subjectRepository.save(Subject.toInsertEntity("생명과학1", "Y"));
        subjectRepository.save(Subject.toInsertEntity("생명과학2", "Y"));
        subjectRepository.save(Subject.toInsertEntity("화학1", "Y"));
        subjectRepository.save(Subject.toInsertEntity("화학2", "N"));

        List<SubjectsDto> subjects = adminSubjectService.findSubjects();

        assertThat(subjects).hasSize(4);
    }

    @Test
    void delete_Test(){
        final Subject enrolled = subjectRepository.save(Subject.toInsertEntity("생명과학1", "Y"));

        adminSubjectService.deleteSubject(enrolled.getId());
        final Optional<Subject> deleted = subjectRepository.findById(enrolled.getId());

        assertThat(deleted.isEmpty()).isTrue();
    }




}




