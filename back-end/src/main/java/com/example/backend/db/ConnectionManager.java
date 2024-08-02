package com.example.backend.db;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {
    private static ConnectionManager instance;
    private DataSource dataSource;
    private Connection connection;

    private ConnectionManager() {
        try {
            var initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/thogakade");
            this.connection = dataSource.getConnection(); // Optionally, you might want to handle connection pooling here
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
