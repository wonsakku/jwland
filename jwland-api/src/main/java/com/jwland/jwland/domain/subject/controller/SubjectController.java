package com.jwland.jwland.domain.subject.controller;

import com.jwland.jwland.domain.subject.dto.SubjectDto;
import com.jwland.jwland.domain.subject.dto.SubjectProblemTypeDto;
import com.jwland.jwland.domain.subject.dto.SubjectsDto;
import com.jwland.jwland.domain.subject.service.SubjectService;
import com.jwland.jwland.dto.DefaultResponseDto;
import com.jwland.jwland.util.ErrorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping
    public ResponseEntity<DefaultResponseDto> getSubjects(){
        List<SubjectsDto> subjects = subjectService.findSubjects();
        return ResponseEntity.ok( new DefaultResponseDto(HttpStatus.OK, subjects) );
    }

    @PostMapping
    public ResponseEntity<DefaultResponseDto> enrollSubject(@RequestBody @Validated SubjectDto subjectDto, Errors errors){

        ErrorUtil.validate(errors);

        subjectService.enrollSubject(subjectDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new DefaultResponseDto(HttpStatus.CREATED));
    }

    @PutMapping
    public ResponseEntity<DefaultResponseDto> updateSubject(@RequestBody @Validated SubjectDto subjectDto, Errors errors){

        ErrorUtil.validate(errors);

        subjectService.updateSubject(subjectDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new DefaultResponseDto(HttpStatus.OK));
    }

    @DeleteMapping("/{subjectId}")
    public ResponseEntity<DefaultResponseDto> deleteSubject(@PathVariable Long subjectId){


        subjectService.deleteSubject(subjectId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

//    @GetMapping("/problem-types")
//    public ResponseEntity<DefaultResponseDto> enrollProblemTypes(@RequestBody @Validated SubjectProblemTypeDto subjectProblemTypeDto,
//                                                                 Errors errors){
//        ErrorUtil.validate(errors);
//        subjectService
//
//    }



}











