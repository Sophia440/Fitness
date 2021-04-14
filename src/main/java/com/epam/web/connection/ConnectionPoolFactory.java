package com.epam.web.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionPoolFactory {
    private static final String PROPERTIES_PATH = "src/main/resources/db.properties";

    private static Driver driver;
    private String mysqlUrl;
    private String mysqlUsername;
    private String mysqlPassword;

    private int poolSize;
    private static final int DEFAULT_POOL_SIZE = 5;

    ConnectionPoolFactory() {

    }

    ConnectionPool createPool() throws ConnectionException {
        try {
            initializeProperties();
            List<ProxyConnection> connections = createConnections();
            return new ConnectionPool(poolSize, connections);
        } catch (SQLException | IOException exception) {
            throw new ConnectionException(exception.getMessage(), exception);
        }
    }

    private void initializeProperties() throws SQLException, IOException {
        Properties properties = new Properties();
        FileInputStream inputStream = new FileInputStream(PROPERTIES_PATH);
        properties.load(inputStream);
        driver = new com.mysql.cj.jdbc.Driver();
        DriverManager.registerDriver(driver);
        this.mysqlUrl = properties.getProperty("url");
        this.mysqlUsername = properties.getProperty("username");
        this.mysqlPassword = properties.getProperty("password");
        this.poolSize =  Integer.parseInt(properties.getProperty("poolSize"));
        if (this.poolSize == 0) {
            this.poolSize = DEFAULT_POOL_SIZE;
        }
    }

    private List createConnections() throws SQLException {
        List<ProxyConnection> connections = new ArrayList<>();
        for (int i = 0; i < poolSize; i++) {
            Connection connection = DriverManager.getConnection(mysqlUrl, mysqlUsername, mysqlPassword);
            ProxyConnection proxyConnection = new ProxyConnection(connection);
            connections.add(proxyConnection);
        }
        return connections;
    }

    static void deregisterDriver() throws SQLException {
        DriverManager.deregisterDriver(driver);
    }
}
