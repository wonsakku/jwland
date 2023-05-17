package com.jwland.jwland.domain.exam.dto;

import com.jwland.jwland.entity.ExamOrganization;
import com.jwland.jwland.entity.status.ExamOrganizationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class ExamOrganizationDto {

    private Long organizationId;

    @NotNull
    private String organizationType;

    @NotNull
    private String name;

    @NotNull
    private Integer seq;

    public ExamOrganization toInsertEntity(){
        return ExamOrganization.insertEntity(ExamOrganizationType.findByName(organizationType), name, seq);
    }

    public ExamOrganization toUpdateEntity(){
        return ExamOrganization.updateEntity(organizationId, ExamOrganizationType.findByName(organizationType), name, seq);
    }

    public ExamOrganizationDto(ExamOrganization examOrganization){
        this.organizationId = examOrganization.getId();
        this.organizationType = examOrganization.getExamOrganizationType().name();
        this.name = examOrganization.getName();
        this.seq = examOrganization.getSeq();
    }
}
