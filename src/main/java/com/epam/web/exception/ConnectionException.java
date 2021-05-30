package com.epam.web.exception;

/**
 * A connection exception class. An instance of it may be thrown from ConnectionPool or ConnectionPoolFactory classes.
 *
 */
public class ConnectionException extends Exception {

    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(Throwable cause) {
        super(cause);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
