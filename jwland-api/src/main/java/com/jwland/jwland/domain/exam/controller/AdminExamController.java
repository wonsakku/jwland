package com.jwland.jwland.domain.exam.controller;

import com.jwland.jwland.domain.exam.dto.*;
import com.jwland.jwland.domain.exam.service.AdminExamService;
import com.jwland.jwland.dto.DefaultResponseDto;
import com.jwland.jwland.util.ErrorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/exams")
public class AdminExamController {

    private final AdminExamService adminExamService;

    @PostMapping
    public ResponseEntity<DefaultResponseDto> enrollExam(@RequestBody @Validated ExamDto examDto,
                                                         Errors errors){

        ErrorUtil.validate(errors);
        adminExamService.enrollExam(examDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body( new DefaultResponseDto(HttpStatus.CREATED) );
    }

    @GetMapping
    public ResponseEntity<DefaultResponseDto> getExams(Pageable pageable){
        Page<ExamsDto> results = adminExamService.getExams(pageable);
        return ResponseEntity.ok( new DefaultResponseDto(HttpStatus.OK, results) );
    }

    @GetMapping("/{examId}")
    public ResponseEntity<DefaultResponseDto> getExamDetail(@PathVariable Long examId){
        ExamDto result = adminExamService.getExamDetail(examId);
        return ResponseEntity.ok( new DefaultResponseDto(HttpStatus.OK, result) );
    }

    @PutMapping
    public ResponseEntity<DefaultResponseDto> updateExam(@RequestBody @Validated ExamDto examDto,
                                                         Errors errors){
        ErrorUtil.validate(errors);
        adminExamService.updateExam(examDto);
        return ResponseEntity.ok( new DefaultResponseDto( HttpStatus.OK ) );
    }

    @DeleteMapping("/{examId}")
    public ResponseEntity<DefaultResponseDto> deleteExam(@PathVariable Long examId){
        adminExamService.deleteExam(examId);
        return ResponseEntity.ok( new DefaultResponseDto(HttpStatus.OK) );
    }
    @GetMapping("/{examId}/enrolled-subjects")
    public ResponseEntity<DefaultResponseDto> getEnrolledExamSubjects(@PathVariable Long examId){
        List<EnrolledExamSubjectDto> results = adminExamService.getEnrolledExamSubjects(examId);
        return ResponseEntity.ok( new DefaultResponseDto(HttpStatus.OK, results) );
    }

    @GetMapping("/{examId}/unenrolled-subjects")
    public ResponseEntity<DefaultResponseDto> getUnEnrolledExamSubjects(@PathVariable Long examId){
        List<UnenrolledExamSubjectDto> results = adminExamService.getUnEnrolledExamSubjects(examId);
        return ResponseEntity.ok( new DefaultResponseDto(HttpStatus.OK, results) );
    }

    @PostMapping("/subjects")
    public ResponseEntity<DefaultResponseDto> enrollExamSubject(@RequestBody @Validated ExamSubjectDto examSubjectDto,
                                                                Errors errors){
        ErrorUtil.validate(errors);
        adminExamService.enrollExamSubject(examSubjectDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body( new DefaultResponseDto(HttpStatus.CREATED) );
    }

    @PutMapping("/subjects")
    public ResponseEntity<DefaultResponseDto> updateExamSubject(@RequestBody @Validated ExamSubjectDto examSubjectDto,
                                                                Errors errors){
        ErrorUtil.validate(errors);
        adminExamService.updateExamSubject(examSubjectDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body( new DefaultResponseDto(HttpStatus.OK) );
    }

    @DeleteMapping("/subjects/{examSubjectId}")
    public ResponseEntity<DefaultResponseDto> deleteExamSubject(@PathVariable Long examSubjectId){
        adminExamService.deleteExamSubject(examSubjectId);
        return ResponseEntity.ok( new DefaultResponseDto(HttpStatus.OK) );
    }


    @GetMapping("/subjects/{examSubjectId}/problem-types")
    public ResponseEntity<DefaultResponseDto> getSubjectProblemTypes(@PathVariable Long examSubjectId,
                                                                     @RequestParam(name = "problemClassification") String problemClassification){
        List<ExamSubjectProblemTypeDto> results = adminExamService.getSubjectProblemTypes(examSubjectId, problemClassification);
        return ResponseEntity.ok( new DefaultResponseDto(HttpStatus.OK, results) );
    }

    @PostMapping("/subjects/{examSubjectId}/problems")
    public ResponseEntity<DefaultResponseDto> enrollExamProblems(@RequestBody @Validated ExamProblemListDto examProblemListDto,
                                                                 Errors errors,
                                                                 @PathVariable Long examSubjectId){
        ErrorUtil.validate(errors);
        adminExamService.enrollExamProblems(examProblemListDto, examSubjectId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new DefaultResponseDto( HttpStatus.OK ) );
    }

    @GetMapping("/subjects/{examSubjectId}/problems")
    public ResponseEntity<DefaultResponseDto> getExamProblems(@PathVariable Long examSubjectId){
        List<ExamProblemDto> results = adminExamService.getExamProblems(examSubjectId);
        return ResponseEntity.ok( new DefaultResponseDto(HttpStatus.OK, results) );
    }


}







