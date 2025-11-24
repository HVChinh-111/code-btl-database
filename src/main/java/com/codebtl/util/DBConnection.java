package com.codebtl.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        String URL = "jdbc:mysql://localhost:3306/btl_database1";
        String USER = "root";
        String PASSWORD = "1112005@ChInH";
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
