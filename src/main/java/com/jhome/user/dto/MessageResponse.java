package com.jhome.user.dto;

import com.jhome.user.constant.ResponseCode;
import lombok.Getter;

@Getter
public final class MessageResponse<D> {
    final private int code;
    final private String message;
    final private D data;

    private MessageResponse(int code, String message, D data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <D> MessageResponse<?> of(ResponseCode responseCode, D data) {
        return new MessageResponse<>(responseCode.getCode() , responseCode.getMessage(), data);
    }

    public static <D> MessageResponse<?> of(ResponseCode responseCode) {
        return new MessageResponse<>(responseCode.getCode() , responseCode.getMessage(), null);
    }
}
