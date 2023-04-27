package com.jwland.jwland.entity.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AttendanceStatus {
    ATTENDANCE("출석"), LATE("지각"), ABSENCE("결석"),
    ;

    private final String status;
}
