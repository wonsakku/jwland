package com.jwland.jwland.domain.lesson.service;

import com.jwland.jwland.constant.Time;
import com.jwland.jwland.domain.account.repository.AccountRepository;
import com.jwland.jwland.domain.lesson.dto.*;
import com.jwland.jwland.domain.lesson.repository.AccountLessonEnrollStatusRepository;
import com.jwland.jwland.domain.lesson.repository.LessonAttendanceDateRepository;
import com.jwland.jwland.domain.lesson.repository.LessonAttendanceRepository;
import com.jwland.jwland.domain.lesson.repository.LessonRepository;
import com.jwland.jwland.domain.subject.repository.SubjectRepository;
import com.jwland.jwland.entity.*;
import com.jwland.jwland.entity.status.AttendanceStatus;
import com.jwland.jwland.entity.status.EnrollStatus;
import com.jwland.jwland.entity.status.Grade;
import com.jwland.jwland.entity.status.SchoolClassification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminLessonService {

    private final LessonCommonService lessonCommonService;
    private final LessonRepository lessonRepository;
    private final SubjectRepository subjectRepository;
    private final AccountRepository accountRepository;
    private final LessonAttendanceRepository lessonAttendanceRepository;
    private final LessonAttendanceDateRepository lessonAttendanceDateRepository;
    private final AccountLessonEnrollStatusRepository accountLessonEnrollStatusRepository;

    @Transactional
    public Long enrollLesson(LessonDto lessonDto) {
        Subject subject = subjectRepository.findById(lessonDto.getSubjectId())
                .orElseThrow(() -> new IllegalArgumentException("일치하는 subject 가 없습니다."));

        Lesson lesson = lessonDto.toInsertEntity(subject);
        Lesson saved = lessonRepository.save(lesson);
        return saved.getId();
    }


    @Transactional
    public Long updateLesson(LessonDto lessonDto) {
        Subject subject = subjectRepository.findById(lessonDto.getSubjectId())
                .orElseThrow(() -> new IllegalArgumentException("일치하는 subject 가 존재하지 않습니다."));

        Lesson updating = lessonDto.toUpdateEntity(subject);
        Lesson target = lessonCommonService.getLesson(updating.getId());

        target.changeData(updating);

        return target.getId();
    }

    public LessonDetailDto getLessonDetail(Long lessonId) {
        Lesson lesson = lessonCommonService.getLesson(lessonId);
        return new LessonDetailDto(lesson);
    }


    @Transactional
    public void deleteLesson(Long lessonId) {
        Lesson targetLesson = lessonCommonService.getLesson(lessonId);

        if(targetLesson.isAfterOpen()){
            throw new IllegalStateException("수업 삭제는 개강 전에만 가능합니다.");
        }

        lessonRepository.delete(targetLesson);
    }

    public Page<AccountLessonDto> getUnenrolledAccounts(Long lessonId, String schoolClassification, String grade, String name, Pageable pageable) {
        final Page<Account> unenrolledAccounts = accountRepository.findAccountsNotInLesson(
                lessonId,
                SchoolClassification.findByName(schoolClassification),
                Grade.findByNumber(grade),
                name,
                pageable
        );

        return unenrolledAccounts.map(AccountLessonDto::new);
    }

    @Transactional
    public void enrollAccountsToLesson(Long lessonId, AccountIdsEnrollingLessonDto accountIdsEnrollingLessonDto) {

        final Lesson lesson = lessonCommonService.getUnclosedLesson(lessonId);

        final List<Account> accounts = accountRepository.findAllById(accountIdsEnrollingLessonDto.getAccountIds());

        final List<AccountLessonEnrollStatus> enrolls = accounts.stream()
                .map(account -> new AccountLessonEnrollStatus(account, lesson, EnrollStatus.ENROLL))
                .collect(Collectors.toList());

        for (AccountLessonEnrollStatus enroll : enrolls) {
            accountLessonEnrollStatusRepository.save(enroll);
        }

    }

    public List<AccountLessonDto> getEnrolledAccounts(Long lessonId) {
        List<AccountLessonEnrollStatus> accountLessonEnrollStatus = accountLessonEnrollStatusRepository.findByLessonId(lessonId);
        final List<AccountLessonDto> results = accountLessonEnrollStatus.stream()
                .map(status -> new AccountLessonDto(status.getAccount()))
                .collect(Collectors.toList());
        return results;
    }

    @Transactional
    public void enrollAttendance(Long lessonId, LessonAttendanceDto lessonAttendanceDto) {

        final Lesson lesson = lessonCommonService.getUnclosedLesson(lessonId);
        final String todayDateFormat = new SimpleDateFormat(Time.Pattern.YYYYMMDD).format(new Date());

        lessonAttendanceDateRepository.findTop1ByLessonAndStartDate(lesson, todayDateFormat)
                .ifPresent(entity -> {throw new IllegalArgumentException("오늘 날짜의 출석체크는 이미 진행했습니다.\n출석 변경은 출석 수정 페이지에서 진행해주세요");});

        final LessonAttendanceDate lessonAttendanceDate = lessonAttendanceDateRepository.save(new LessonAttendanceDate(lesson, todayDateFormat));

        final List<Account> enrolledAccounts = accountRepository.findAllById(lessonAttendanceDto.getAccountIds());

        for (Account enrolledAccount : enrolledAccounts) {
            final String attendanceStatus = lessonAttendanceDto.getAttendanceStatusByAccountId(enrolledAccount.getId());
            final LessonAttendance lessonAttendance = new LessonAttendance(enrolledAccount,
                    lessonAttendanceDate,
                    AttendanceStatus.findByName(attendanceStatus));
            lessonAttendanceRepository.save(lessonAttendance);
        }
    }


    public List<LessonAttendanceDateDto> getAttendanceDate(Long lessonId) {
        final Lesson lesson = lessonCommonService.getLesson(lessonId);
        List<LessonAttendanceDate> date = lessonAttendanceDateRepository.findByLessonOrderByStartDateDesc(lesson);
        return date.stream()
                .map(LessonAttendanceDateDto::new)
                .collect(Collectors.toList());
    }

    public List<LessonAttendanceInfoDto> getLessonAttendanceDate(Long lessonAttendanceDateId) {
        List<LessonAttendance> lessonAttendance = lessonAttendanceRepository.findAttendanceWithAccountById(lessonAttendanceDateId);
        return lessonAttendance.stream()
                .map(LessonAttendanceInfoDto::new)
                .collect(Collectors.toList());


    }

    @Transactional
    public void updateLessonAttendance(Long lessonAttendanceDateId, LessonAttendanceDto lessonAttendanceDto) {

        final LessonAttendanceDate lessonAttendanceDate = lessonAttendanceDateRepository.findById(lessonAttendanceDateId)
                .orElseThrow(() -> new IllegalArgumentException("선택한 날짜의 출석 데이터가 존재하지 않습니다."));
        final List<Account> enrolledAccounts = accountRepository.findAllById(lessonAttendanceDto.getAccountIds());
        final Accounts accounts = new Accounts(enrolledAccounts);

        final List<LessonAttendance> enrolledLessonAttendances = lessonAttendanceRepository.findByLessonAttendanceDate(lessonAttendanceDate);

        for (LessonAttendance enrolledLessonAttendance : enrolledLessonAttendances) {
            final Account enrolledAccount = accounts.findByAccountId(enrolledLessonAttendance.getAccount());
            final String attendanceStatus = lessonAttendanceDto.getAttendanceStatusByAccountId(enrolledAccount.getId());
            final LessonAttendance updating = new LessonAttendance(enrolledAccount, lessonAttendanceDate, AttendanceStatus.findByName(attendanceStatus));
            enrolledLessonAttendance.update(updating);
        }
    }
}
