package com.example.user_servicer.DBConnection;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Singleton
@Startup
public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/lms_user_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Om@r30102001";

    private static Connection connection;

    public static synchronized Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (ClassNotFoundException e) {
                throw new SQLException("Failed to establish database connection", e);
            }
        }
        return connection;
    }
}
