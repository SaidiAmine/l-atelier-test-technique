package com.org.latelier.exceptions;

public class ExternalApiCallException extends RuntimeException {
    public ExternalApiCallException(String message) {
        super(message);
    }
}
