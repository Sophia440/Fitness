package com.epam.web.connection;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private Queue<ProxyConnection> availableConnections;
    private Queue<ProxyConnection> connectionsInUse;

    private ReentrantLock connectionsLock = new ReentrantLock();

    private ConnectionPool() {
        this.availableConnections = new ArrayDeque<>();
        this.connectionsInUse = new ArrayDeque<>();
    }

    public static ConnectionPool getInstance() {
        throw new UnsupportedOperationException();
    }

    public void returnConnection(ProxyConnection proxyConnection) {
        connectionsLock.lock();
        try {
            if (connectionsInUse.contains(proxyConnection)) {
                availableConnections.offer(proxyConnection);
            }
        } finally {
            connectionsLock.unlock();
        }
    }

    public ProxyConnection getConnection() {
        return ConnectionFactory.create();
    }
}
