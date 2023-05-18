package com.jwland.jwland.domain.subject.repository;

import com.jwland.jwland.entity.Subject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional(readOnly = true)
@SpringBootTest
class SubjectRepositoryTest {


    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    EntityManager em;

    @Transactional
    @Test
    @DisplayName("JpaRepository 가 아닌 Repository 쿼리 테스트 - save")
    void repositoryTest(){
        final Subject subject1 = Subject.toInsertEntity("과목1", "Y");
        final Subject subject2 = Subject.toInsertEntity("과목2", "Y");

        subjectRepository.save(subject1);
        subjectRepository.save(subject2);

        em.flush();
        em.clear();

        final Subject enrolled1 = em.find(Subject.class, subject1.getId());
        final Subject enrolled2 = em.find(Subject.class, subject2.getId());


        assertThat(subject1.getId()).isEqualTo(enrolled1.getId());
        assertThat(subject1.getName()).isEqualTo(enrolled1.getName());
        assertThat(subject1.getUseYn()).isEqualTo(enrolled1.getUseYn());

        assertThat(subject2.getId()).isEqualTo(enrolled2.getId());
        assertThat(subject2.getName()).isEqualTo(enrolled2.getName());
        assertThat(subject2.getUseYn()).isEqualTo(enrolled2.getUseYn());
    }

    @Test
    @DisplayName("Repository - where 문 및 orderBy 테스트")
    void repository_where_and_orderBy_Test(){

        // r y o b -> b d o r y
        subjectRepository.save(Subject.toInsertEntity("r", "Y"));
        subjectRepository.save(Subject.toInsertEntity("a", "N"));
        subjectRepository.save(Subject.toInsertEntity("t", "N"));
        subjectRepository.save(Subject.toInsertEntity("b", "Y"));
        subjectRepository.save(Subject.toInsertEntity("y", "Y"));
        subjectRepository.save(Subject.toInsertEntity("z", "N"));
        subjectRepository.save(Subject.toInsertEntity("o", "Y"));
        subjectRepository.save(Subject.toInsertEntity("d", "Y"));

        em.flush();
        em.clear();

        List<Subject> subjects = subjectRepository.findSubjectsByUseYnOrderByName("Y");

        assertThat(subjects).hasSize(5);
        assertThat(subjects.get(0).getName()).isEqualTo("b");
        assertThat(subjects.get(1).getName()).isEqualTo("d");
        assertThat(subjects.get(2).getName()).isEqualTo("o");
        assertThat(subjects.get(3).getName()).isEqualTo("r");
        assertThat(subjects.get(4).getName()).isEqualTo("y");
    }

    @Test
    void pagination_Test(){
        subjectRepository.save(Subject.toInsertEntity("u", "N"));
        subjectRepository.save(Subject.toInsertEntity("r", "Y"));
        subjectRepository.save(Subject.toInsertEntity("t", "N"));
        subjectRepository.save(Subject.toInsertEntity("a", "N"));
        subjectRepository.save(Subject.toInsertEntity("b", "Y"));
        subjectRepository.save(Subject.toInsertEntity("l", "N"));
        subjectRepository.save(Subject.toInsertEntity("q", "N"));
        subjectRepository.save(Subject.toInsertEntity("y", "Y"));
        subjectRepository.save(Subject.toInsertEntity("z", "N"));
        subjectRepository.save(Subject.toInsertEntity("o", "Y"));
        subjectRepository.save(Subject.toInsertEntity("j", "N"));
        subjectRepository.save(Subject.toInsertEntity("d", "Y"));
        subjectRepository.save(Subject.toInsertEntity("k", "N"));

        em.flush();
        em.clear();

        final PageRequest pageRequest = PageRequest.of(0, 4, Sort.by(Sort.Direction.DESC, "name"));

        // when
        Page<Subject> subjectPage = subjectRepository.findSubjectsByUseYn("N", pageRequest);
        final List<Subject> subjects = subjectPage.getContent();

        // then ==> z u t q l k j a
        assertThat(subjects).hasSize(4);
        assertThat(subjects.get(0).getName()).isEqualTo("z");
        assertThat(subjects.get(1).getName()).isEqualTo("u");
        assertThat(subjects.get(2).getName()).isEqualTo("t");
        assertThat(subjects.get(3).getName()).isEqualTo("q");
    }


}











