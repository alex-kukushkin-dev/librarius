package com.demo.library.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ValidationException extends RuntimeException{
    private String message;
    private List<ValidationError> fields;
}