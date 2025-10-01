package com.gradetracker.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/grade_tracker";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "15N@va86"; // <-- Double-check this password!

    static {
        try {
            // Explicitly load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // This error will pop-up if the .jar file is not in the classpath
            JOptionPane.showMessageDialog(null, 
                "MySQL JDBC Driver not found!\nMake sure the connector .jar file is in your classpath.",
                "Driver Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DB_USER, DB_PASSWORD);
    }
}