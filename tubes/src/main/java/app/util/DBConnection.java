package app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Kelas utilitas untuk membuat koneksi ke database MySQL menggunakan JDBC.
 * Konsep PBO: Package (app.util) - kelas ini dikelompokkan sebagai utilitas.
 */
public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/db_penjualan?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // sesuaikan dengan password MySQL Anda

    private static Connection connection;

    // Private constructor agar kelas ini tidak bisa di-instantiate (utility class)
    private DBConnection() {
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException e) {
                throw new SQLException("Driver MySQL JDBC tidak ditemukan.", e);
            }
        }
        return connection;
    }
}
