package com.ssafy.cozytrain.common.exception;

public class FoundException extends RuntimeException {
    public FoundException(String message) {
        super(message);
    }

    public FoundException(String message, Throwable cause) {
        super(message, cause);
    }
}