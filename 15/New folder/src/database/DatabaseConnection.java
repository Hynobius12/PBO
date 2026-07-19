package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/db_futsal";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Silakan sesuaikan password database Anda
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Gagal terhubung ke database: " + e.getMessage());
            }
        }
        return connection;
    }
}