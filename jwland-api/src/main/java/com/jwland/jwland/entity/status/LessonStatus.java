package com.jwland.jwland.entity.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum LessonStatus {
    BEFORE_OPEN("준비중"), OPEN("개강"), COMPLETED("완강"), SHUTDOWN("폐강");

    private final String status;

    public static LessonStatus findByName(String name){
        return Stream.of(values())
                .filter(value -> value.name().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 lessonStatus 의 name 이 없습니다."));
    }

    public boolean isAfterOpen() {
        return this != BEFORE_OPEN;
    }

    public boolean isClosed(){
        return this == COMPLETED || this == SHUTDOWN;
    }
}


