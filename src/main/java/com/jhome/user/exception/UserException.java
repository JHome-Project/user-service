package com.jhome.user.exception;

import com.jhome.user.constant.ResponseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserException extends RuntimeException{

    private final ResponseCode responseCode;

}
