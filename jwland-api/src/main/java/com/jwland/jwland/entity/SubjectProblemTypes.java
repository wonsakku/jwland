package com.jwland.jwland.entity;

import lombok.Getter;

import java.util.List;

@Getter
public class SubjectProblemTypes {

    private List<SubjectProblemType> subjectProblemTypes;

    public SubjectProblemTypes(List<SubjectProblemType> subjectProblemTypes) {
        this.subjectProblemTypes = subjectProblemTypes;
    }

    public SubjectProblemType findById(Long subjectProblemTypeId){
        return this.subjectProblemTypes.stream()
                .filter(subjectProblemType -> subjectProblemType.getId().equals(subjectProblemTypeId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 subjectProblemTypeId 가 없습니다."))
                ;
    }
}
