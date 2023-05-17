package com.jwland.jwland.domain.lesson.controller;

import com.jwland.jwland.domain.lesson.dto.LessonDetailDto;
import com.jwland.jwland.domain.lesson.dto.LessonDto;
import com.jwland.jwland.domain.lesson.dto.LessonSubjectsDto;
import com.jwland.jwland.domain.lesson.dto.LessonsDto;
import com.jwland.jwland.domain.lesson.service.LessonService;
import com.jwland.jwland.dto.DefaultResponseDto;
import com.jwland.jwland.dto.EnumDto;
import com.jwland.jwland.entity.status.Grade;
import com.jwland.jwland.entity.status.LessonStatus;
import com.jwland.jwland.util.ErrorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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





    @GetMapping("/subjects")
    public ResponseEntity<DefaultResponseDto> getLessonSubjects(){
        List<LessonSubjectsDto> subjects = lessonService.getLessonSubjects();
        return ResponseEntity.ok( new DefaultResponseDto(HttpStatus.OK, subjects) );
    }



}





