package com.jwland.jwland.entity.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProblemClassification {
    LARGE("대분류(단원명)", 1), MIDDLE("중분류(챕터명)", 2), SMALL("소분류(문제_상세)", 3);


    private final String type;
    private final Integer level;

}
