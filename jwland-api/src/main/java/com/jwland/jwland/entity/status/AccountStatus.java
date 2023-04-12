package com.jwland.jwland.entity.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum AccountStatus {

    APPROVAL_REQUEST("승인요청"),
    APPROVED("승인"),
    DORMANT("휴면"),
    DELETED("삭제")
    ;

    private final String status;

    public static AccountStatus findByName(String accountStatusName) {
        return Stream.of(values())
                .filter(value -> value.name().equals(accountStatusName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정 상태입니다."))
                ;
    }
}
