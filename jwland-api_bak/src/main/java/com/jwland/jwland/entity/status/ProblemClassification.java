package com.jwland.jwland.entity.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum ProblemClassification {
    LARGE("대분류(단원명)", 1), MIDDLE("중분류(챕터명)", 2), SMALL("소분류(문제_상세)", 3);


    private final String type;
    private final Integer level;

    public static ProblemClassification findByName(String name){
        return Stream.of(values())
                .filter(value -> value.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ProblemClassification 입니다. - " + name));
    }

    public boolean topLevel() {
        return this.level == 1;
    }
}


