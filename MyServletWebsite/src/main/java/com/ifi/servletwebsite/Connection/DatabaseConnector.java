package com.ifi.servletwebsite.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    public static Connection initializeDatabase() throws SQLException, ClassNotFoundException
    {
        String dbURL = "jdbc:mysql://localhost:3306/";
        String dbName = "servlet";
        String dbUsername = "root";
        String dbPassword = "ifiSolution@98A";

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(dbURL + dbName, dbUsername, dbPassword);
        return connection;
    }
}
