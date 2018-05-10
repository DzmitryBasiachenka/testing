package com.bsdim.web.project.connection;

import java.sql.Connection;

public class ConnectionContext {
    private static final ThreadLocal<Connection> THREAD_CONNECTION = new ThreadLocal<>();

    public static Connection getConnection() {
        Connection connection = THREAD_CONNECTION.get();
        if (null == connection) {
            ConnectionManager manager = ConnectionManager.getInstance();
            connection = manager.getConnection();
            THREAD_CONNECTION.set(connection);
        }
        return  connection;
    }

    public static void releaseConnection() {
        ConnectionManager manager = ConnectionManager.getInstance();
        manager.putConnection(THREAD_CONNECTION.get());
    }
}
