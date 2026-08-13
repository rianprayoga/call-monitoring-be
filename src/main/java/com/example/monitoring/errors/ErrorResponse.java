package com.example.monitoring.errors;


import com.example.monitoring.errors.http.HttpException;

public record ErrorResponse(int code, String description, String message) {

    public static ErrorResponse from(HttpException e) {
        ErrorCode errorCode = e.getErrorCode();
        return new ErrorResponse(errorCode.getCode(), errorCode.getDescription(), e.getMessage());
    }

    public static ErrorResponse of(ErrorCode errorCode, String message) {
        return new ErrorResponse(errorCode.getCode(), errorCode.getDescription(), message);
    }

}
