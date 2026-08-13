package com.example.monitoring.errors.http;


import com.example.monitoring.errors.ErrorCode;

import static org.springframework.http.HttpStatus.FORBIDDEN;

public class ForbiddenException extends HttpException {
    public ForbiddenException(ErrorCode errorCode, String message) {
        super(FORBIDDEN, errorCode, message);
    }

    public ForbiddenException(ErrorCode errorCode, String message, Throwable cause) {
        super(FORBIDDEN, errorCode, message, cause);
    }
}
