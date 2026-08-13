package com.example.monitoring.errors;


import lombok.Getter;

public enum ErrorCode  {
    SCHEMA_VALIDATION_FAILED(400_01_001, "schemaValidationFailed"),
    INVALID_CURSOR(400_01_002, "invalidCursor"),
    METHOD_NOT_ALLOWED(405_01_000, "methodNotAllowed"),
    INTERNAL_SERVER_ERROR(500_01_000, "internalServerError");

    @Getter
    private final Integer code;
    private final String description;

    ErrorCode(Integer code, String description) {
        this.code = code;
        this.description = description;
    }


    public String getDescription() {
        return this.name();
    }



}
