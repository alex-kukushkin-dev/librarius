package com.demo.library.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final String CONTACT_ADMINISTRATION_MESSAGE = "There is a problem on server side. Please contact service administration.";
    private static final String VALIDATION_FAILED_MESSAGE = "Validation failed for one or more fields.";

    @ExceptionHandler(UserFriendlyException.class)
    public ResponseEntity<?> handleUserFriendlyException(UserFriendlyException ex) {
        return createErrorResponse(BAD_REQUEST, ex.getMessage(), ex.getDescription());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleException(EntityNotFoundException ex) {
        return createErrorResponse(NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleExceptions(MethodArgumentNotValidException ex) {
        var validationError = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> new ValidationError(e.getField(), e.getDefaultMessage(), null))
                .collect(Collectors.toList());
        return createErrorResponse(BAD_REQUEST, VALIDATION_FAILED_MESSAGE, null, validationError);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAll(Exception e) {
        log.error("Exception handler: ", e);
        return createErrorResponse(INTERNAL_SERVER_ERROR, CONTACT_ADMINISTRATION_MESSAGE);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException ex) {
        return createErrorResponse(UNPROCESSABLE_ENTITY, ex.getMessage(), null, ex.getFields());
    }

    private ResponseEntity<ErrorResponse> createErrorResponse(HttpStatus httpStatus, String message) {
        return createErrorResponse(httpStatus, message, null);
    }

    private ResponseEntity<ErrorResponse> createErrorResponse(HttpStatus httpStatus, String message, String details) {
        return createErrorResponse(httpStatus, message, details, null);
    }

    private ResponseEntity<ErrorResponse> createErrorResponse(HttpStatus httpStatus, String message, String details, List<ValidationError> validationErrors) {
        return ResponseEntity.status(httpStatus)
                .body(new ErrorResponse(message, details, validationErrors));
    }
}
