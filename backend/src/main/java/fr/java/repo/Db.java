package fr.java.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    private static final String URL =
            "jdbc:mysql://localhost:3306/java_restau?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = ""; // souvent vide

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
