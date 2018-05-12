package com.bsdim.web.project.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;

public final class ConnectionManager {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "db.url";
    private static final String DB_USER_NAME = "db.user.name";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_CONFIG_PATH = "db.config.path";
    private static final String DB_CONFIG_PROPERTIES = "/dbConfig.properties";
    private static final int CAPACITY = 10;

    private static ConnectionManager sInstance = new ConnectionManager();

    private Properties properties;
    private ArrayBlockingQueue<Connection> usedConnectionsQueue = new ArrayBlockingQueue<>(CAPACITY);
    private ArrayBlockingQueue<Connection> freeConnectionsQueue = new ArrayBlockingQueue<>(CAPACITY);

    private ConnectionManager() {
        try {
            loadDbConfigProperties();
            Class.forName(DRIVER).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("con manager");//throw new RepositoryException(e);
        }
    }

    private void loadDbConfigProperties() {
        try {
            properties = new Properties();
            properties.load(getDbConfig());
        } catch (IOException e) {
            System.out.println("load");//throw new SystemException(e);
        }
    }

    private InputStream getDbConfig() {
        try {
            String configPropertiesPath = System.getProperty(DB_CONFIG_PATH);
            if (configPropertiesPath != null) {
                return new FileInputStream(configPropertiesPath);
            }
            return getClass().getResourceAsStream(DB_CONFIG_PROPERTIES);
        } catch (IOException e) {
            throw new RuntimeException();//throw new SystemException(e);
        }
    }

    public Connection getConnection() {
        try {
            Connection connection = usedConnectionsQueue.poll();
            if (connection == null) {
                if (freeConnectionsQueue.remainingCapacity() > usedConnectionsQueue.size()) {
                    connection = DriverManager.getConnection(properties.getProperty(DB_URL),
                            properties.getProperty(DB_USER_NAME), properties.getProperty(DB_PASSWORD));
                } else {
                    connection = usedConnectionsQueue.take();
                }
            }
            freeConnectionsQueue.put(connection);
            return connection;
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void putConnection(Connection connection) {
        try {
            connection.setAutoCommit(true);
            usedConnectionsQueue.put(connection);
            freeConnectionsQueue.remove(connection);
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionManager getInstance() {
        return sInstance;
    }
}
