package com.bsdim.web.project.connection;

import java.sql.Connection;

/**
 * The connection context.
 * <p>
 * Date: 2018-05-20
 *
 * @author Dzmitry Basiachenka
 */
public final class ConnectionContext {
    private static final ThreadLocal<Connection> THREAD_CONNECTION = new ThreadLocal<>();

    private ConnectionContext() {}

    /**
     * Gets connection.
     *
     * @return the connection.
     */
    public static Connection getConnection() {
        Connection connection = THREAD_CONNECTION.get();
        if (connection == null) {
            ConnectionManager manager = ConnectionManager.getInstance();
            connection = manager.getConnection();
            THREAD_CONNECTION.set(connection);
        }
        return connection;
    }

    /**
     * Releases connection.
     */
    public static void releaseConnection() {
        ConnectionManager manager = ConnectionManager.getInstance();
        manager.putConnection(THREAD_CONNECTION.get());
        THREAD_CONNECTION.remove();
    }
}
