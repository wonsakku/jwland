package com.jwland.jwland.entity.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
}
