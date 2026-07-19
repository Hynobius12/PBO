package service;

import database.DatabaseConnection;
import java.sql.*;

public class ReservasiService {
    private Connection conn = DatabaseConnection.getConnection();

    public void buatReservasi(int idPelanggan, int idLapangan, String tanggal, String jam) {
        // Cek apakah lapangan tersedia terlebih dahulu
        String cekStatusSql = "SELECT status FROM lapangan WHERE id_lapangan = ?";
        try (PreparedStatement cekStmt = conn.prepareStatement(cekStatusSql)) {
            cekStmt.setInt(1, idLapangan);
            try (ResultSet rs = cekStmt.executeQuery()) {
                if (rs.next()) {
                    if (!rs.getString("status").equalsIgnoreCase("Tersedia")) {
                        System.out.println("Gagal! Lapangan sedang tidak tersedia (Terpakai).");
                        return;
                    }
                } else {
                    System.out.println("ID Lapangan tidak ditemukan!");
                    return;
                }
            }
            
            // Lakukan Reservasi, Trigger 'update_status_lapangan' otomatis akan mengubah status menjadi Terpakai
            String query = "INSERT INTO reservasi (id_pelanggan, id_lapangan, tanggal, jam) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, idPelanggan);
                stmt.setInt(2, idLapangan);
                stmt.setString(3, tanggal);
                stmt.setString(4, jam);
                stmt.executeUpdate();
                System.out.println("Reservasi sukses dilakukan! Status lapangan ter-update otomatis oleh Trigger.");
            }
        } catch (SQLException e) {
            System.out.println("Error saat transaksi reservasi: " + e.getMessage());
        }
    }

    // Menampilkan riwayat reservasi terintegrasi memanfaatkan Database View riwayat_reservasi
    public void lihatRiwayatReservasi() {
        String query = "SELECT * FROM riwayat_reservasi";
        System.out.println("\n=== RIWAYAT RESERVASI (VIEW DATABASE) ===");
        System.out.printf("%-4s | %-20s | %-15s | %-12s | %-8s | %-10s\n", "ID", "Pelanggan", "Lapangan", "Tanggal", "Jam", "Status Lap.");
        System.out.println("--------------------------------------------------------------------------------");
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.printf("%-4d | %-20s | %-15s | %-12s | %-8s | %-10s\n",
                    rs.getInt("id_reservasi"),
                    rs.getString("nama_pelanggan"),
                    rs.getString("nama_lapangan"),
                    rs.getDate("tanggal").toString(),
                    rs.getTime("jam").toString(),
                    rs.getString("status")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error mengambil riwayat: " + e.getMessage());
        }
        
        // Memanfaatkan Database Function total_reservasi() untuk menghitung jumlah data
        tampilkanTotalReservasi();
    }

    private void tampilkanTotalReservasi() {
        String sql = "SELECT total_reservasi() AS total";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                System.out.println("--------------------------------------------------------------------------------");
                System.out.println("Total Akumulasi Transaksi Sewa (Dari Database Function): " + rs.getInt("total") + " kali.");
            }
        } catch (SQLException e) {
            System.out.println("Error memanggil function database: " + e.getMessage());
        }
    }
}