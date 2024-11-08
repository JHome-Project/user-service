package com.jhome.user.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {

    SUCCESS("성공", 0),
    FAIL("실패", -1);

    private final String message;
    private final int code;

}
