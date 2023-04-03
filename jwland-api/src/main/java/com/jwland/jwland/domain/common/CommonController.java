package com.jwland.jwland.domain.common;

import com.jwland.jwland.dto.DefaultResponseDto;
import com.jwland.jwland.dto.EnumDto;
import com.jwland.jwland.entity.status.Grade;
import com.jwland.jwland.entity.status.LessonStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@RestController
@RequestMapping("/common")
public class CommonController {

    private final CommonService commonService;


    @GetMapping("/target-grades")
    public ResponseEntity<DefaultResponseDto> getTargetGrades(){
        List<EnumDto> data = Stream.of(Grade.values()).map(value -> EnumDto.builder()
                        .code(value.name())
                        .name(value.getGrade())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok( new DefaultResponseDto(HttpStatus.OK, data) );
    }
    @GetMapping("/lesson-status")
    public ResponseEntity<DefaultResponseDto> getLessonStatus(){
        List<EnumDto> status = Stream.of(LessonStatus.values()).map(value -> EnumDto.builder()
                        .code(value.name())
                        .name(value.getStatus())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok( new DefaultResponseDto(HttpStatus.OK, status) );
    }

    @GetMapping("/schools")
    public ResponseEntity<DefaultResponseDto> getSchools(){
        List<EnumDto> results = commonService.getSchools();

        return ResponseEntity.ok( new DefaultResponseDto( HttpStatus.OK, results ) );
    }


}
