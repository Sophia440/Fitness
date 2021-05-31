package com.epam.web.connection;

import com.epam.web.exception.ConnectionException;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class initiates a connection pool.
 *
 */
public class ConnectionPoolFactory {
    private static Driver driver;
    private static final String MYSQL_URL = "jdbc:mysql://localhost:3307/fitness_db?useSSL=false&serverTimezone=Europe/Minsk";
    private static final String MYSQL_USERNAME = "root";
    private static final String MYSQL_PASSWORD = "root";
    private static final int POOL_SIZE = 6;

    ConnectionPoolFactory() {

    }

    /**
     * Creates a connection pool.
     *
     * @return ConnectionPool the first instance of a connection pool
     */
    ConnectionPool createPool() throws ConnectionException {
        try {
            driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            List<ProxyConnection> connections = createConnections();
            return new ConnectionPool(POOL_SIZE, connections);
        } catch (SQLException exception) {
            throw new ConnectionException(exception.getMessage(), exception);
        }
    }

    /**
     * Creates a list of connections for the pool.
     *
     * @return List of ProxyConnections
     */
    private List createConnections() throws SQLException {
        List<ProxyConnection> connections = new ArrayList<>();
        for (int i = 0; i < POOL_SIZE; i++) {
            Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
            ProxyConnection proxyConnection = new ProxyConnection(connection);
            connections.add(proxyConnection);
        }
        return connections;
    }

    /**
     * Deregisters the driver.
     *
     */
    static void deregisterDriver() throws SQLException {
        DriverManager.deregisterDriver(driver);
    }
}
