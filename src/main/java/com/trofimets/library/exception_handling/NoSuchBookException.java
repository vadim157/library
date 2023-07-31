package com.trofimets.library.exception_handling;

public class NoSuchBookException extends NoSuchEntityException {
    public NoSuchBookException(String message) {
        super(message);
    }
}
