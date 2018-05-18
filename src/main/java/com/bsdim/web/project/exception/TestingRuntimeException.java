package com.bsdim.web.project.exception;

public class TestingRuntimeException extends RuntimeException {

    public TestingRuntimeException() {
        super();
    }

    public TestingRuntimeException(Throwable exception) {
        super(exception);
    }

    public TestingRuntimeException(String message) {
        super(message);
    }

    public TestingRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
