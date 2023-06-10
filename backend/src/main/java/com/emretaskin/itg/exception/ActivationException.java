package com.emretaskin.itg.exception;

public class ActivationException extends RuntimeException {
    public ActivationException(String message) {
        super(message);
    }

    public ActivationException(String message, Throwable cause) {
        super(message, cause);
    }
}