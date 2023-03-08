package com.jwland.jwlandapi.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum LessonStatus {
    BEFORE_OPEN("준비중"), OPEN("개강"), CLOSED("완강");

    private final String name;
}
