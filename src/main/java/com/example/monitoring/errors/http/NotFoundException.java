package com.example.monitoring.errors.http;


import com.example.monitoring.errors.ErrorCode;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class NotFoundException extends HttpException {

    public NotFoundException(ErrorCode errorCode, String message) {
        super(NOT_FOUND, errorCode, message);
    }

    public NotFoundException(ErrorCode errorCode, String message, Throwable cause) {
        super(NOT_FOUND, errorCode, message, cause);
    }
}
