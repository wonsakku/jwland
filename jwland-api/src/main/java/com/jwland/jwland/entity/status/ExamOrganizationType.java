package com.jwland.jwland.entity.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum ExamOrganizationType {
    SAT("수능", "Y"),
    EVALUATOR("평가원", "Y"),
    EDUCATIONAL_OFFICE("교육청", "Y"),
    PRIVATE("사설", "Y"),
    ;

    private final String type;
    private final String activateYn;

    public boolean activated(){
        return this.activateYn.equalsIgnoreCase("Y");
    }

    public static ExamOrganizationType findByName(String input){
        return Stream.of(values())
                .filter(value -> value.name().equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 시험기관 입니다."));
    }

}
