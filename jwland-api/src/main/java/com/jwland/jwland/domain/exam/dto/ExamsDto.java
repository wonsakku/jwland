package com.jwland.jwland.domain.exam.dto;

import com.jwland.jwland.entity.Exam;
import lombok.Getter;

@Getter
public class ExamsDto {
    private Long examId;
    private Integer year;
    private Integer month;
    private String organizationName;

    public ExamsDto(Exam exam){
        this.examId = exam.getId();
        this.year = exam.getYear();
        this.month = exam.getMonth();
        this.organizationName = exam.getOrganization().getName();
    }

}
