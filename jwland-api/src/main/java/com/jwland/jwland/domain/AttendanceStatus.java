package com.jwland.jwland.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AttendanceStatus {
    ATTENDANCE("출석"), LATE("지각"), ABSENCE("결석"),
    ;

    private final String name;
}
