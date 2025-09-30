package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/grade_tracker?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // change this
    private static final String PASSWORD = "15N@va86"; // change this

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

