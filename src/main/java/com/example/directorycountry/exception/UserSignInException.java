package com.example.directorycountry.exception;

import org.springframework.http.HttpStatus;

public class UserSignInException extends Throwable {
    public UserSignInException(String s, HttpStatus notFound) {
    }
}
