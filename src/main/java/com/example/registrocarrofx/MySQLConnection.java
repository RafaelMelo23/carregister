package com.example.registrocarrofx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    public static final String url = "jdbc:mysql://localhost:3306/CarRegister";
    private static final String user = "root";
    private static final String password = "0000";

    public static Connection getMySqlConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}