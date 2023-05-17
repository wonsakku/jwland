package com.jwland.jwland.entity.status;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EnrollStatus {
    CANCEL("취소"), ENROLL("등록"),
    ;

    private final String name;
}
