package com.acm.web.exception;


import com.acm.web.enums.ResponseEnum;
import com.acm.web.vo.ResponseVo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;


@RestControllerAdvice
public class RuntimeExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseVo<Object> handle(RuntimeException e) {
        return ResponseVo.error(ResponseEnum.ERROR, e.getMessage());
    }

    @ExceptionHandler(UserLoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseVo<Object> handle() {
        return ResponseVo.error(ResponseEnum.NEED_LOGIN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseVo<Object> notValidExceptionHandle(MethodArgumentNotValidException e) {
        return ResponseVo.error(ResponseEnum.PARAM_ERROR, Objects.requireNonNull(e.getBindingResult().getFieldError()).getField() + " " + e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(ParseException.class)
    public ResponseVo<Object> parseExceptionHandle() {
        return ResponseVo.error(ResponseEnum.TOKEN_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseVo<Object> illegalArgumentHandle() {
        return ResponseVo.error(ResponseEnum.PARAM_ERROR);
    }
}
