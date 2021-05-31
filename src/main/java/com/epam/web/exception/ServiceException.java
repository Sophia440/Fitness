package com.epam.web.exception;

/**
 * A service exception class. An instance of it may be thrown from service classes.
 *
 */
public class ServiceException extends Exception {

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}