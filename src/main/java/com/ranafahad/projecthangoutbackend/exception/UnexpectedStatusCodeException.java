package com.ranafahad.projecthangoutbackend.exception;

public class UnexpectedStatusCodeException extends RuntimeException{

    public UnexpectedStatusCodeException(String message) {
        super(message);
    }
}
