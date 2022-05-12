package com.example.directorycountry.exception;

import org.springframework.http.HttpStatus;

public class EmailNotBeEmptyException extends RuntimeException {

    public EmailNotBeEmptyException(String email_is_empty, HttpStatus email_not_be_empty) {
    }

    public EmailNotBeEmptyException(String message) {
        super(message);
    }

    public EmailNotBeEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
