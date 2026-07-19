package service;

import database.DatabaseConnection;
import model.Lapangan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LapanganService {
    private Connection conn = DatabaseConnection.getConnection();

    // Menggunakan Stored Procedure sesuai ketentuan deskripsi teknis
    public void tambahLapangan(String nama, String jenis) {
        String sql = "{CALL tambah_lapangan(?, ?)}";
        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, nama);
            stmt.setString(2, jenis);
            stmt.execute();
            System.out.println("Lapangan baru berhasil ditambahkan melalui Stored Procedure!");
        } catch (SQLException e) {
            System.out.println("Error tambah lapangan: " + e.getMessage());
        }
    }

    public List<Lapangan> getAllLapangan() {
        List<Lapangan> list = new ArrayList<>();
        String query = "SELECT * FROM lapangan";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                list.add(new Lapangan(
                    rs.getInt("id_lapangan"),
                    rs.getString("nama_lapangan"),
                    rs.getString("jenis_lapangan"),
                    rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error ambil data lapangan: " + e.getMessage());
        }
        return list;
    }
}