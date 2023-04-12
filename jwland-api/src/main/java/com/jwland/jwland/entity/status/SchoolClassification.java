package com.jwland.jwland.entity.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum SchoolClassification {

    ELEMENTARY("초등학교", "Y"),
    MIDDLE("중학교", "Y"),
    HIGH("고등학교", "Y"),
    ADMIN("재원랜드", "N")
    ;
    private final String value;
    private final String showYn;

    public static SchoolClassification findByName(String schoolClassificationName) {
        return Stream.of(values())
                .filter(value -> value.name().equals(schoolClassificationName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 학교 분류가 없습니다."))
                ;
    }
}
