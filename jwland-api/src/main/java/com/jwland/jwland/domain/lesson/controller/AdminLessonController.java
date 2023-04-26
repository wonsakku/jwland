package com.jwland.jwland.domain.lesson.controller;

import com.jwland.jwland.domain.lesson.dto.LessonDto;
import com.jwland.jwland.domain.lesson.service.AdminLessonService;
import com.jwland.jwland.dto.DefaultResponseDto;
import com.jwland.jwland.util.ErrorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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


    @DeleteMapping("/{lessonId}")
    public ResponseEntity<DefaultResponseDto> deleteLesson(@PathVariable Long lessonId){
        adminLessonService.deleteLesson(lessonId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

