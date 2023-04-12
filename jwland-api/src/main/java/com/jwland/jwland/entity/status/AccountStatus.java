package com.jwland.jwland.entity.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AccountStatus {

    APPROVAL_REQUEST("승인요청"),
    APPROVED("승인"),
    DORMANT("휴면"),
    DELETED("삭제")
    ;

    private final String status;
}
