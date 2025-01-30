package com.demo.library.exception;

import lombok.Getter;

@Getter
public class UserFriendlyException extends RuntimeException {

    private String description;

    public UserFriendlyException(String message) {
        super(message);
    }

    public UserFriendlyException(String message, String description) {
        super(message);
        this.description = description;
    }
}
