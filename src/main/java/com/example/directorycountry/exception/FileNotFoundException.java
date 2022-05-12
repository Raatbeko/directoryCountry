package com.example.directorycountry.exception;

import org.springframework.http.HttpStatus;

public class FileNotFoundException extends RuntimeException{
    public FileNotFoundException(String file_not_found, HttpStatus notFound) {
    }

    public FileNotFoundException(String message) {
        super(message);
    }
}
