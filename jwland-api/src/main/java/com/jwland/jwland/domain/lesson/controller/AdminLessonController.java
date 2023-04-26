package com.jwland.jwland.domain.lesson.controller;

import com.jwland.jwland.domain.lesson.dto.AccountIdsEnrollingLessonDto;
import com.jwland.jwland.domain.lesson.dto.AccountLessonEnrollStatusDto;
import com.jwland.jwland.domain.lesson.dto.LessonDetailDto;
import com.jwland.jwland.domain.lesson.dto.LessonDto;
import com.jwland.jwland.domain.lesson.service.AdminLessonService;
import com.jwland.jwland.dto.DefaultResponseDto;
import com.jwland.jwland.util.ErrorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/lessons")
public class AdminLessonController {

    private final AdminLessonService adminLessonService;
    @PostMapping
    public ResponseEntity<DefaultResponseDto> enrollLesson(@RequestBody @Validated LessonDto lessonDto, Errors errors){
        ErrorUtil.validate(errors);
        adminLessonService.enrollLesson(lessonDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body( new DefaultResponseDto(HttpStatus.CREATED) );
    }


    @PutMapping
    public ResponseEntity<DefaultResponseDto> updateLesson(@RequestBody @Validated LessonDto lessonDto, Errors errors){
        ErrorUtil.validate(errors);
        adminLessonService.updateLesson(lessonDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body( new DefaultResponseDto(HttpStatus.OK) );
    }

    @GetMapping("/{lessonId}")
    public ResponseEntity<DefaultResponseDto> getLessonDetail(@PathVariable Long lessonId){
        LessonDetailDto lessonDetail =  adminLessonService.getLessonDetail(lessonId);
        return ResponseEntity.ok( new DefaultResponseDto(HttpStatus.OK, lessonDetail) );
    }




    @DeleteMapping("/{lessonId}")
    public ResponseEntity<DefaultResponseDto> deleteLesson(@PathVariable Long lessonId){
        adminLessonService.deleteLesson(lessonId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{lessonId}/unenrolled-accounts")
    public ResponseEntity<DefaultResponseDto> getUnenrolledAccounts(
            @PathVariable("lessonId") Long lessonId,
            @RequestParam(name = "schoolClassification") String schoolClassification,
            @RequestParam(name = "grade") String grade,
            @RequestParam(name = "name") String name,
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable
            ){

        Page<AccountLessonEnrollStatusDto> results = adminLessonService.getUnenrolledAccounts(lessonId, schoolClassification, grade, name, pageable);

        return ResponseEntity.ok( new DefaultResponseDto(HttpStatus.OK, results) );
    }

    @PostMapping("/{lessonId}/accounts/enroll")
    public ResponseEntity<DefaultResponseDto> enrollAccountsToLesson(
            @PathVariable("lessonId") Long lessonId,
            @RequestBody @Validated AccountIdsEnrollingLessonDto accountIdsEnrollingLessonDto,
            Errors errors
            ){
        ErrorUtil.validate(errors);
        adminLessonService.enrollAccountsToLesson(lessonId, accountIdsEnrollingLessonDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body( new DefaultResponseDto(HttpStatus.OK) );
    }
}

