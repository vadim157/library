package com.trofimets.library.exception_handling;

public class NoSuchUserException extends NoSuchEntityException {
    public NoSuchUserException(String message) {
        super(message);
    }
}
