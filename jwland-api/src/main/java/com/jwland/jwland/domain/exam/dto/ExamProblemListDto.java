package com.jwland.jwland.domain.exam.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ExamProblemListDto {

    private List<@Valid ExamProblemSubDto> examProblems;

    public List<Long> getExamProblemIds() {
        return this.examProblems.stream()
                .map(ExamProblemSubDto::getExamProblemId)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<Long> getSubjectProblemTypeIds(){
        return this.examProblems.stream()
                .map(ExamProblemSubDto::getSubjectProblemTypeId)
                .collect(Collectors.toList());
    }

    public ExamProblemSubDto getSubDtoByExamProblemId(Long examProblemId) {
        return this.examProblems.stream()
                .filter(subDto -> examProblemId.equals(subDto.getExamProblemId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 값이 없습니다."));
    }

    public List<ExamProblemSubDto> getSubDtosNotHavingExamProblemId() {
        return this.examProblems.stream()
                .filter(subDto -> subDto.getExamProblemId() == null)
                .collect(Collectors.toList());
    }
}
