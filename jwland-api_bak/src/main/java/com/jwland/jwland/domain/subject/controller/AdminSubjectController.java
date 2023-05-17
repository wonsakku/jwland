package com.jwland.jwland.domain.subject.controller;

import com.jwland.jwland.domain.subject.dto.SubjectProblemTypeDto;
import com.jwland.jwland.domain.subject.dto.SubjectProblemTypeListDto;
import com.jwland.jwland.domain.subject.service.AdminSubjectService;
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

    private final AdminSubjectService adminSubjectService;

    @PostMapping("/problem-types")
    public ResponseEntity<DefaultResponseDto> enrollProblemType(@RequestBody @Validated SubjectProblemTypeDto subjectProblemTypeDto,
                                                                Errors errors){
        ErrorUtil.validate(errors);

        adminSubjectService.enrollProblemType(subjectProblemTypeDto);

        return ResponseEntity.status(HttpStatus.CREATED).body( new DefaultResponseDto(HttpStatus.OK) );
    }

    @GetMapping("/problem-types")
    public ResponseEntity<DefaultResponseDto> getProblemTypes(@RequestParam(name = "parentId", required = false) Long parentId,
                                                              @RequestParam(name = "problemClassification") String problemClassification,
                                                              @RequestParam(name = "subjectId") Long subjectId){
        List<SubjectProblemTypeListDto> results = adminSubjectService.getProblemTypes(parentId, problemClassification, subjectId);
        return ResponseEntity.ok( new DefaultResponseDto(HttpStatus.OK, results) );
    }

    @PutMapping("/problem-types")
    public ResponseEntity<DefaultResponseDto> updateProblemType(@RequestBody @Validated SubjectProblemTypeDto subjectProblemTypeDto,
                                                                Errors errors){
        ErrorUtil.validate(errors);

        adminSubjectService.updateProblemType(subjectProblemTypeDto);

        return ResponseEntity.status(HttpStatus.CREATED).body( new DefaultResponseDto(HttpStatus.OK) );
    }


}
