package com.bsdim.web.project.exception;

/**
 * The testing runtime exception.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public class TestingRuntimeException extends RuntimeException {

    /**
     * The constructor.
     */
    public TestingRuntimeException() {
        super();
    }

    /**
     * Parametrized constructor.
     *
     * @param exception the exception.
     */
    public TestingRuntimeException(Throwable exception) {
        super(exception);
    }

    /**
     * Parametrized constructor.
     *
     * @param message the message.
     */
    public TestingRuntimeException(String message) {
        super(message);
    }

    /**
     * Parametrized constructor.
     *
     * @param message the message.
     * @param cause the exception.
     */
    public TestingRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
