package com.jwland.jwland.domain.exam.dto;

import com.jwland.jwland.entity.Exam;
import com.jwland.jwland.entity.ExamOrganization;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class ExamDto {

    private Long examId;

    @NotNull
    private Integer year;

    @NotNull
    private Integer month;

    @NotNull
    private Long organizationId;


    public Exam toEntity(ExamOrganization examOrganization) {
        return Exam.toInsertEntity(this.year, this.month, examOrganization);
    }


    public ExamDto(Exam exam){
        this.examId = exam.getId();
        this.year = exam.getYear();
        this.month = exam.getMonth();
        this.organizationId = exam.getOrganization().getId();
    }
}
