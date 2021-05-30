package com.epam.web.exception;

/**
 * A dao exception class. An instance of it may be thrown from dao classes.
 *
 */
public class DaoException extends Exception {

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(String message) {
        super(message);
    }
}