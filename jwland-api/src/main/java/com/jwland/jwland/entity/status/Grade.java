package com.jwland.jwland.entity.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.jwland.jwland.entity.status.SchoolClassification.*;

@Getter
@RequiredArgsConstructor
public enum Grade {

    ONE("1학년", Arrays.asList(ELEMENTARY, MIDDLE, HIGH)),
    TWO("2학년", Arrays.asList(ELEMENTARY, MIDDLE, HIGH)),
    THREE("3학년", Arrays.asList(ELEMENTARY, MIDDLE, HIGH)),
    FOUR("4학년", Arrays.asList(ELEMENTARY)),
    FIVE("5학년", Arrays.asList(ELEMENTARY)),
    SIX("6학년", Arrays.asList(ELEMENTARY)),
    JWLAND("관리자", Arrays.asList(ADMIN))
    ;

    private final String grade;
    private final List<SchoolClassification> involves;

    public static Grade findByNumber(String gradeNumber) {
        return Stream.of(values())
                .filter(value -> value.name().equals(gradeNumber))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 학년이 없습니다."))
                ;
    }

    public static List<Grade> findBySchoolClassification(String schoolClassification) {
        return Stream.of(values())
                .filter(value -> value.involves.contains(SchoolClassification.findByName(schoolClassification)))
                .collect(Collectors.toList());
    }
}
