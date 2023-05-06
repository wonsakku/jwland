package com.jwland.jwland.domain.exam.controller;

import com.jwland.jwland.domain.exam.dto.ExamOrganizationDto;
import com.jwland.jwland.domain.exam.service.AdminExamOrganizationService;
import com.jwland.jwland.dto.DefaultResponseDto;
import com.jwland.jwland.entity.ExamOrganization;
import com.jwland.jwland.util.ErrorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/admin/exams")
public class AdminExamOrganizationController {

    private final AdminExamOrganizationService adminExamOrganizationService;

    @PostMapping("/organizations")
    public ResponseEntity<DefaultResponseDto> enrollExamOrganization(@RequestBody @Validated ExamOrganizationDto examOrganizationDto,
                                                                     Errors errors){
        ErrorUtil.validate(errors);
        adminExamOrganizationService.enrollExamOrganization(examOrganizationDto);

        return ResponseEntity.ok( new DefaultResponseDto(HttpStatus.CREATED) );
    }

    @GetMapping("/organizations")
    public ResponseEntity<DefaultResponseDto> getExamOrganization(){
        List<ExamOrganizationDto> results = adminExamOrganizationService.getExamOrganization();
        return ResponseEntity.ok( new DefaultResponseDto(HttpStatus.OK, results) );
    }


    @PutMapping("/organizations")
    public ResponseEntity<DefaultResponseDto> updateExamOrganization(@RequestBody @Validated ExamOrganizationDto examOrganizationDto,
                                                                     Errors errors){
        ErrorUtil.validate(errors);
        adminExamOrganizationService.updateExamOrganization(examOrganizationDto);

        return ResponseEntity.ok( new DefaultResponseDto(HttpStatus.OK) );
    }

}
