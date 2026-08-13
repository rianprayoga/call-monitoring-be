package com.example.monitoring.errors.http;

import com.example.monitoring.errors.ErrorCode;
import org.springframework.http.HttpStatus;

public class HttpException extends RuntimeException {

    private final HttpStatus status;
    private final ErrorCode errorCode;

    HttpException(HttpStatus status, ErrorCode errorCode, String message) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
    }

    HttpException(HttpStatus status, ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.errorCode = errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
