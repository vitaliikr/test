package com.eeze.video.exception;

public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException() {
        super();
    }

    public InvalidRequestException( String message) {
        super(message);
    }
}
