package com.gradetracker.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/gradetracker";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "15N@va86";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DB_USER, DB_PASSWORD);
    }
}