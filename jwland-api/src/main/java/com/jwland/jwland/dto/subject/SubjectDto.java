package com.jwland.jwland.dto.subject;

import com.jwland.jwland.entity.Subject;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

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

    public SubjectDto(String name) {
        this.name = name;
    }

    public Subject toEnrollDto() {
        return Subject.toInsertEntity(this.name);
    }

    public Subject toUpdateEntity() {
        return Subject.toUpdateEntity(this.id, this.name, this.useYn);
    }
}



