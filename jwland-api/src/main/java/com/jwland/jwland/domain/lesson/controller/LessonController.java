package com.jwland.jwland.domain.lesson.controller;

import com.jwland.jwland.domain.lesson.dto.LessonDetailDto;
import com.jwland.jwland.domain.lesson.dto.LessonDto;
import com.jwland.jwland.domain.lesson.dto.LessonsDto;
import com.jwland.jwland.domain.lesson.service.LessonService;
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

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService lessonService;

    @GetMapping
    public ResponseEntity<DefaultResponseDto> getLessons(){
        List<LessonsDto> lessons = lessonService.getLessons();
        return ResponseEntity.ok( new DefaultResponseDto(HttpStatus.OK, lessons) );
    }


    @GetMapping("/{lessonId}")
    public ResponseEntity<DefaultResponseDto> getLessonDetail(@PathVariable Long lessonId){
        LessonDetailDto lessonDetail =  lessonService.getLessonDetail(lessonId);
        return ResponseEntity.ok( new DefaultResponseDto(HttpStatus.OK, lessonDetail) );
    }


    @PostMapping
    public ResponseEntity<DefaultResponseDto> enrollLesson(@RequestBody @Validated LessonDto lessonDto, Errors errors){
        ErrorUtil.validate(errors);
        lessonService.enrollLesson(lessonDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body( new DefaultResponseDto(HttpStatus.CREATED) );
    }

    @PutMapping
    public ResponseEntity<DefaultResponseDto> updateLesson(@RequestBody @Validated LessonDto lessonDto, Errors errors){
        ErrorUtil.validate(errors);
        lessonService.updateLesson(lessonDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body( new DefaultResponseDto(HttpStatus.OK) );
    }

    @DeleteMapping("/{lessonId}")
    public ResponseEntity<DefaultResponseDto> deleteLesson(@PathVariable Long lessonId){
        lessonService.deleteLesson(lessonId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}




