package com.jwland.jwland.domain.exam.controller;

import com.jwland.jwland.domain.exam.dto.ExamDto;
import com.jwland.jwland.domain.exam.dto.ExamsDto;
import com.jwland.jwland.domain.exam.service.AdminExamService;
import com.jwland.jwland.dto.DefaultResponseDto;
import com.jwland.jwland.util.ErrorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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


}
