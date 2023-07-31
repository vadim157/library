package com.trofimets.library.exception_handling;

public class NoSuchEntityException extends RuntimeException {
    public NoSuchEntityException(String message) {
        super(message);
    }
}
