package com.example.tddspring.exceptions;

public class WrongUserCredentialsException extends Exception {
    public WrongUserCredentialsException(String message) {
        super(message);
    }
}
