package com.example.monitoring.errors.http;


import com.example.monitoring.errors.ErrorCode;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class BadRequestException extends HttpException {

    public BadRequestException(ErrorCode errorCode, String message) {
        super(BAD_REQUEST, errorCode, message);
    }

    public BadRequestException(ErrorCode errorCode, String message, Throwable cause) {
        super(BAD_REQUEST, errorCode, message, cause);
    }
}
