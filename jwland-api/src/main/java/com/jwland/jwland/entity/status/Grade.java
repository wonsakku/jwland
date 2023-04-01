package com.jwland.jwland.entity.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum Grade {

    MIDDLE_1("중1"),
    MIDDLE_2("중2"),
    MIDDLE_3("중3"),
    HIGH_1("고1"),
    HIGH_2("고2"),
    HIGH_3("고3"),
    ;

    private final String grade;

    public static Grade findByName(String gradeName) {
        return Stream.of(values())
                .filter(value -> value.name().equals(gradeName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 grade 의 name 이 없습니다."))
                ;
    }
}
