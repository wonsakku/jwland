package com.jwland.jwland.entity.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum LessonType {

    COMMON_SCIENCE("공통과학", "Y"),
    LIFE_1("생명과학1", "Y"),
    LIFE_2("생명과학2", "Y"),
    CHEM_1("화학1", "Y"),
    CHEM_2("화학2", "Y"),
    ;
    private final String lesson;
    private final String useYn;

    private static final String Y = "Y";
    private static final String N = "N";

    public static LessonType findByValue(String findValue) {
        return Stream.of(values())
                .filter(value -> value.name().equals(findValue))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("일치하는 수업 코드가 없습니다.(%s)", findValue)));
    }

}





