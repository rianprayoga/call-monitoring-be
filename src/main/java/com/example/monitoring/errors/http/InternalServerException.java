package com.example.monitoring.errors.http;


import com.example.monitoring.errors.ErrorCode;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class InternalServerException extends HttpException {

    public InternalServerException(ErrorCode errorCode, String message) {
        super(INTERNAL_SERVER_ERROR, errorCode, message);
    }

    public InternalServerException(ErrorCode errorCode, String message, Throwable cause) {
        super(INTERNAL_SERVER_ERROR, errorCode, message, cause);
    }
}
