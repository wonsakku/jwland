package com.jwland.jwland.domain.lesson.service;

import com.jwland.jwland.domain.account.repository.AccountRepository;
import com.jwland.jwland.domain.lesson.dto.AccountIdsEnrollingLessonDto;
import com.jwland.jwland.domain.lesson.dto.AccountLessonEnrollStatusDto;
import com.jwland.jwland.domain.lesson.dto.LessonDetailDto;
import com.jwland.jwland.domain.lesson.dto.LessonDto;
import com.jwland.jwland.domain.lesson.repository.AccountLessonEnrollStatusRepository;
import com.jwland.jwland.domain.lesson.repository.LessonRepository;
import com.jwland.jwland.domain.subject.repository.SubjectRepository;
import com.jwland.jwland.entity.Account;
import com.jwland.jwland.entity.AccountLessonEnrollStatus;
import com.jwland.jwland.entity.Lesson;
import com.jwland.jwland.entity.Subject;
import com.jwland.jwland.entity.status.EnrollStatus;
import com.jwland.jwland.entity.status.Grade;
import com.jwland.jwland.entity.status.SchoolClassification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminLessonService {

    private final LessonRepository lessonRepository;
    private final SubjectRepository subjectRepository;
    private final AccountRepository accountRepository;
    private final AccountLessonEnrollStatusRepository accountLessonEnrollStatusRepository;

    @Transactional
    public Long enrollLesson(LessonDto lessonDto) {
        Long subjectId = lessonDto.getSubjectId();
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 subject 가 없습니다."));

        Lesson lesson = lessonDto.toInsertEntity(subject);
        Lesson saved = lessonRepository.save(lesson);
        return saved.getId();
    }


    @Transactional
    public Long updateLesson(LessonDto lessonDto) {
        Long subjectId = lessonDto.getSubjectId();
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 subject 가 존재하지 않습니다."));

        Lesson updating = lessonDto.toUpdateEntity(subject);
        Lesson target = lessonRepository.findById(updating.getId())
                .orElseThrow(() -> new IllegalArgumentException("일치하는 lesson 이 존재하지 않습니다."));

        target.changeData(updating);

        return target.getId();
    }

    public LessonDetailDto getLessonDetail(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 lessonId 입니다.."));

        return new LessonDetailDto(lesson);
    }


    @Transactional
    public void deleteLesson(Long lessonId) {
        Lesson targetLesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 lessonId 입니다."));

        if(targetLesson.isAfterOpen()){
            throw new IllegalStateException("수업 삭제는 개강 전에만 가능합니다.");
        }

        lessonRepository.delete(targetLesson);
    }

    public Page<AccountLessonEnrollStatusDto> getUnenrolledAccounts(Long lessonId, String schoolClassification, String grade, String name, Pageable pageable) {
        final Page<Account> unenrolledAccounts = accountRepository.findAccountsNotInLesson(
                lessonId,
                SchoolClassification.findByName(schoolClassification),
                Grade.findByNumber(grade),
                name,
                pageable
        );

        return unenrolledAccounts.map(AccountLessonEnrollStatusDto::new);
    }

    @Transactional
    public void enrollAccountsToLesson(Long lessonId, AccountIdsEnrollingLessonDto accountIdsEnrollingLessonDto) {
        final Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 lesson입니다."));

        if(lesson.isClosed()){
            throw new IllegalArgumentException("종료된 lesson 입니다.");
        }

        final List<Account> accounts = accountRepository.findAllById(accountIdsEnrollingLessonDto.getAccountIds());

        final List<AccountLessonEnrollStatus> enrolls = accounts.stream()
                .map(account -> new AccountLessonEnrollStatus(account, lesson, EnrollStatus.ENROLL))
                .collect(Collectors.toList());

        for (AccountLessonEnrollStatus enroll : enrolls) {
            accountLessonEnrollStatusRepository.save(enroll);
        }

    }
}
