package com.example.monitoring.errors;


import com.example.monitoring.errors.http.HttpException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.monitoring.errors.ErrorCode.INTERNAL_SERVER_ERROR;
import static com.example.monitoring.errors.ErrorCode.METHOD_NOT_ALLOWED;
import static com.example.monitoring.errors.ErrorCode.SCHEMA_VALIDATION_FAILED;

@Slf4j
@ControllerAdvice
public class ErrorHandler {
    private static final String AN_UNHANDLED_EXCEPTION_HAS_OCCURRED = "An unhandled exception has occurred";

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<ErrorResponse> handleHttpExceptions(HttpException e) {
        if (e.getStatus().is5xxServerError()) {
            log.error(AN_UNHANDLED_EXCEPTION_HAS_OCCURRED, e);
        } else {
            log.debug("Request failed", e);
        }

        return ResponseEntity.status(e.getStatus()).body(ErrorResponse.from(e));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedExceptions(Exception e) {
        log.error(AN_UNHANDLED_EXCEPTION_HAS_OCCURRED, e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(INTERNAL_SERVER_ERROR, "An unexpected error occurred."));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataExceptions(DataIntegrityViolationException e) {
        log.error(AN_UNHANDLED_EXCEPTION_HAS_OCCURRED, e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(INTERNAL_SERVER_ERROR, "An unexpected error occurred."));
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ErrorResponse> handleInvalidRequest(ConstraintViolationException ex) {

        Set<String> errorMessages = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toSet());

        return ResponseEntity.badRequest()
                .body(ErrorResponse.of(SCHEMA_VALIDATION_FAILED, String.join(" ", errorMessages)));
    }


    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleUnsupportedHttpMethod(HttpRequestMethodNotSupportedException ex) {
        String message = "Request method '%s' is not supported.";
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(ErrorResponse.of(METHOD_NOT_ALLOWED, message.formatted(ex.getMethod())));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<String> errorMessages = fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .filter(Strings::isNotBlank)
                .sorted(Comparator.naturalOrder())
                .toList();
        return ResponseEntity.badRequest()
                .body(ErrorResponse.of(SCHEMA_VALIDATION_FAILED, String.join(" ", errorMessages)));
    }

}
