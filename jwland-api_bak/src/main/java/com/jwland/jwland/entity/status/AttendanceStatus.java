package com.jwland.jwland.entity.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum AttendanceStatus {
    ATTENDANCE("출석"), LATE("지각"), ABSENCE("결석"),
    ;

    private final String status;

    public static AttendanceStatus findByName(String attendanceStatus) {
        return Stream.of(values())
                .filter(value -> value.name().equals(attendanceStatus))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 attendanceStatus 입니다."));
    }
}
