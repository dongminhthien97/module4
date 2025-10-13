package com.example.java_aop_booksapp.exception;

public class InvalidBorrowCodeException extends RuntimeException {
    public InvalidBorrowCodeException(String message) {
        super(message);
    }
}
