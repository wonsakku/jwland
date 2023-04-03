package com.jwland.jwland.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public interface CommonCode {

    @Getter
    @RequiredArgsConstructor
    enum GroupCode {
        SCHOOL_CODE("G02"),
        EXAM_CODE("G03")
        ;

        private final String code;
    }
}
