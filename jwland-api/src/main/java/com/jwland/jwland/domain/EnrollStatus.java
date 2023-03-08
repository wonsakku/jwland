package com.jwland.jwland.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EnrollStatus {
    CANCEL("취소"), ENROLL("등록"),
    ;

    private final String name;
}
