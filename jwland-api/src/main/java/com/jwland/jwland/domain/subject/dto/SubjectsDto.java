package com.jwland.jwland.domain.subject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jwland.jwland.constant.Time;
import com.jwland.jwland.entity.Subject;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubjectsDto {

    private Long id;
    private String name;
    private String useYn;
    @JsonFormat(pattern = Time.Pattern.DEFAULT_PATTERN, timezone = Time.Zone.ASIA_SEOUL)
    private LocalDateTime modifiedDateTime;

    public SubjectsDto(Subject subject){
        this.id = subject.getId();
        this.name = subject.getName();
        this.useYn = subject.getUseYn();
        this.modifiedDateTime = subject.getModifiedDateTime();
    }
}


