package com.epam.web.dao;

import com.epam.web.connection.ConnectionPool;
import com.epam.web.exception.ConnectionException;

/**
 * Gets a connection from the pool for dao classes.
 *
 */
public class DaoHelperFactory {
    private final ConnectionPool pool = ConnectionPool.getInstance();

    public DaoHelper create() throws ConnectionException {
        return new DaoHelper(pool.getConnection());
    }
}
