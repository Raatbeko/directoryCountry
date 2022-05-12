package com.example.directorycountry.exception;

import org.springframework.http.HttpStatus;

public class IsAllReadyExistsException extends RuntimeException {

    public IsAllReadyExistsException(String s, HttpStatus it_already_exists) {
    }
}
