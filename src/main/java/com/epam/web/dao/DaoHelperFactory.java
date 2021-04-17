package com.epam.web.dao;

import com.epam.web.connection.ConnectionException;
import com.epam.web.connection.ConnectionPool;

public class DaoHelperFactory {
    private final ConnectionPool pool = ConnectionPool.getInstance();

    public DaoHelper create() throws ConnectionException {
        return new DaoHelper(pool.getConnection());
    }
}
