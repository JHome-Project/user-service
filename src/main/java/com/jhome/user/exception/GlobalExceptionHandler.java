package com.jhome.user.exception;

import com.jhome.user.constant.ResponseCode;
import com.jhome.user.dto.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 파라미터 유효성 검증 에러
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(
                MessageResponse.of(ResponseCode.FAIL));
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<?> handleUserException(Exception e) {
        return ResponseEntity.badRequest().body(
                MessageResponse.of(ResponseCode.FAIL));
    }

}
