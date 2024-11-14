package com.jhome.user.exception;

import com.jhome.user.constant.ResponseCode;
import com.jhome.user.dto.MessageResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    // DB 관련 에러
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<?> handleDataAccessException(DataAccessException e) {
        log.error("[USER ERROR] {}, {}", ResponseCode.USER_DATA_ACCESS_ERROR.getCode(), e.getMessage());
        return ResponseEntity
                .internalServerError()
                .body(MessageResponse.of(ResponseCode.USER_DATA_ACCESS_ERROR));
    }

    // 파라미터 유효성 검증 에러
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException e) {
        log.error("[USER ERROR] {}, {}", ResponseCode.USER_REQUEST_PARAM_INVALID.getCode(), e.getMessage());
        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .toList();

        return ResponseEntity
                .badRequest()
                .body(MessageResponse.of(ResponseCode.USER_REQUEST_PARAM_INVALID, errors));
    }

    // 사용자 에러
    @ExceptionHandler(UserException.class)
    public ResponseEntity<?> handleUserException(UserException e) {
        log.error("[USER ERROR] {}, {}", e.getResponseCode().getCode(), e.getMessage());
        return ResponseEntity
                .badRequest()
                .body(MessageResponse.of(e.getResponseCode()));
    }

}
