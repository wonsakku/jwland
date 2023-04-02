package com.jwland.jwland.domain.subject.dto;

import com.jwland.jwland.entity.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class SubjectDto {

    private Long id;

    @NotNull
    private String name;
    private String useYn;

    public SubjectDto(Long id, String name, String useYn) {
        this.id = id;
        this.name = name;
        this.useYn = useYn;
    }

    public SubjectDto(String name, String useYn) {
        this.name = name;
        this.useYn = useYn;
    }

    public Subject toInsertEntity() {
        return Subject.toInsertEntity(this.name, this.useYn);
    }

    public Subject toUpdateEntity() {
        return Subject.toUpdateEntity(this.id, this.name, this.useYn);
    }
}



