package com.example.monitoring.errors.http;


import com.example.monitoring.errors.ErrorCode;

import static org.springframework.http.HttpStatus.CONFLICT;

public class ConflictException extends HttpException {

    public ConflictException(ErrorCode errorCode, String message) {
        super(CONFLICT, errorCode, message);
    }

    public ConflictException(ErrorCode errorCode, String message, Throwable cause) {
        super(CONFLICT, errorCode, message, cause);
    }
}
