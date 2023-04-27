package com.jwland.jwland.domain.common;

import com.jwland.jwland.dto.DefaultResponseDto;
import com.jwland.jwland.dto.EnumDto;
import com.jwland.jwland.entity.status.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@RestController
@RequestMapping("/common")
public class CommonController {

    private final CommonService commonService;


    @GetMapping("/school-classification")
    public ResponseEntity<DefaultResponseDto<List>> getSchoolClassification(){
        final List<EnumDto> result = SchoolClassification.getVisibleSchools()
                .stream()
                .map(code -> EnumDto.builder()
                        .code(code.name())
                        .name(code.getValue())
                        .build()
                ).collect(Collectors.toList());

        return ResponseEntity.ok( new DefaultResponseDto<>(HttpStatus.OK, result) );

    }

    @GetMapping("/target-grades")
    public ResponseEntity<DefaultResponseDto> getTargetGrades(@RequestParam(name = "schoolClassification") String schoolClassification){
        List<EnumDto> data = Grade.findBySchoolClassification(schoolClassification)
                .stream()
                .map(value -> EnumDto.builder()
                .code(value.name())
                .name(value.getGrade())
                .build()
                ).collect(Collectors.toList());

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

    @GetMapping("/account-status")
    public ResponseEntity<DefaultResponseDto> getAccountStatus(){
        final List<EnumDto> results = Stream.of(AccountStatus.values())
                .map(value -> new EnumDto(value.name(), value.getStatus()))
                .collect(Collectors.toList());

        return ResponseEntity.ok( new DefaultResponseDto( HttpStatus.OK, results ) );
    }

    @GetMapping("/attendance-status")
    public ResponseEntity<DefaultResponseDto> getAttendanceStatus(){
        final List<EnumDto> results = Stream.of(AttendanceStatus.values())
                .map(value -> new EnumDto(value.name(), value.getStatus()))
                .collect(Collectors.toList());

        return ResponseEntity.ok( new DefaultResponseDto( HttpStatus.OK, results ) );
    }


}
