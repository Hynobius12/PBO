package service;

import database.DatabaseConnection;
import model.Pelanggan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PelangganService {
    private Connection conn = DatabaseConnection.getConnection();

    public void tambahPelanggan(String nama, String noTelp) {
        String query = "INSERT INTO pelanggan (nama, nomor_telepon) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nama);
            stmt.setString(2, noTelp);
            stmt.executeUpdate();
            System.out.println("Pelanggan berhasil ditambahkan!");
        } catch (SQLException e) {
            System.out.println("Error tambah pelanggan: " + e.getMessage());
        }
    }

    public List<Pelanggan> getAllPelanggan() {
        List<Pelanggan> list = new ArrayList<>();
        String query = "SELECT * FROM pelanggan";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                list.add(new Pelanggan(
                    rs.getInt("id_pelanggan"),
                    rs.getString("nama"),
                    rs.getString("nomor_telepon")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error ambil data pelanggan: " + e.getMessage());
        }
        return list;
    }
}