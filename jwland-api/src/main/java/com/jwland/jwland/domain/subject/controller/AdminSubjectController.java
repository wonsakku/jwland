package com.jwland.jwland.domain.subject.controller;

import com.jwland.jwland.domain.subject.dto.SubjectDto;
import com.jwland.jwland.domain.subject.dto.SubjectProblemTypeDto;
import com.jwland.jwland.domain.subject.dto.SubjectProblemTypeListDto;
import com.jwland.jwland.domain.subject.dto.SubjectsDto;
import com.jwland.jwland.domain.subject.service.AdminSubjectServiceFacade;
import com.jwland.jwland.domain.subject.service.AdminSubjectServiceImpl;
import com.jwland.jwland.dto.DefaultResponseDto;
import com.jwland.jwland.util.ErrorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/subjects")
public class AdminSubjectController {

    private final AdminSubjectServiceFacade adminSubjectServiceFacade;

    @GetMapping
    public ResponseEntity<DefaultResponseDto> getSubjects(){
        List<SubjectsDto> subjects = adminSubjectServiceFacade.findSubjects();
        return ResponseEntity.ok( new DefaultResponseDto(HttpStatus.OK, subjects) );
    }

    @PostMapping
    public ResponseEntity<DefaultResponseDto> enrollSubject(@RequestBody @Validated SubjectDto subjectDto, Errors errors){

        ErrorUtil.validate(errors);

        adminSubjectServiceFacade.enrollSubject(subjectDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new DefaultResponseDto(HttpStatus.CREATED));
    }

    @PutMapping
    public ResponseEntity<DefaultResponseDto> updateSubject(@RequestBody @Validated SubjectDto subjectDto, Errors errors){

        ErrorUtil.validate(errors);

        adminSubjectServiceFacade.updateSubject(subjectDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new DefaultResponseDto(HttpStatus.OK));
    }

    @DeleteMapping("/{subjectId}")
    public ResponseEntity<DefaultResponseDto> deleteSubject(@PathVariable Long subjectId){


        adminSubjectServiceFacade.deleteSubject(subjectId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PostMapping("/problem-types")
    public ResponseEntity<DefaultResponseDto> enrollProblemType(@RequestBody @Validated SubjectProblemTypeDto subjectProblemTypeDto,
                                                                Errors errors){
        ErrorUtil.validate(errors);

        adminSubjectServiceFacade.enrollProblemType(subjectProblemTypeDto);

        return ResponseEntity.status(HttpStatus.CREATED).body( new DefaultResponseDto(HttpStatus.OK) );
    }

    @GetMapping("/problem-types")
    public ResponseEntity<DefaultResponseDto> getProblemTypes(@RequestParam(name = "parentId", required = false) Long parentId,
                                                              @RequestParam(name = "problemClassification") String problemClassification,
                                                              @RequestParam(name = "subjectId") Long subjectId){
        List<SubjectProblemTypeListDto> results = adminSubjectServiceFacade.getProblemTypes(parentId, problemClassification, subjectId);
        return ResponseEntity.ok( new DefaultResponseDto(HttpStatus.OK, results) );
    }

    @PutMapping("/problem-types")
    public ResponseEntity<DefaultResponseDto> updateProblemType(@RequestBody @Validated SubjectProblemTypeDto subjectProblemTypeDto,
                                                                Errors errors){
        ErrorUtil.validate(errors);

        adminSubjectServiceFacade.updateProblemType(subjectProblemTypeDto);

        return ResponseEntity.status(HttpStatus.CREATED).body( new DefaultResponseDto(HttpStatus.OK) );
    }


}
