package com.jhome.user.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {

    SUCCESS(0,"Success"),
    FAIL(-1,"Fail"),

    // 1xxx: User Exceptions
    USER_REQUEST_PARAM_INVALID(-1000, "User Request Parameter Invalid"),
    USER_ALREADY_EXIST(-1001, "User Already Exist"),
    USER_NOT_FOUND(-1002, "User Not Found"),
    USER_DATA_ACCESS_ERROR(-1003, "User Data Access Error"),
    ;

    private final int code;
    private final String message;
}
