package app.dao;

import app.model.Pelanggan;
import app.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object untuk tabel pelanggan.
 * Menangani CRUD dan pencarian data pelanggan.
 */
public class PelangganDAO {

    public List<Pelanggan> getAll() {
        List<Pelanggan> list = new ArrayList<>();
        String sql = "SELECT * FROM pelanggan ORDER BY id_pelanggan";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mengambil data pelanggan: " + e.getMessage(), e);
        }
        return list;
    }

    public List<Pelanggan> cari(String keyword) {
        List<Pelanggan> list = new ArrayList<>();
        String sql = "SELECT * FROM pelanggan WHERE kode_pelanggan LIKE ? OR nama_pelanggan LIKE ? ORDER BY id_pelanggan";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String k = "%" + keyword + "%";
            ps.setString(1, k);
            ps.setString(2, k);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mencari data pelanggan: " + e.getMessage(), e);
        }
        return list;
    }

    public void tambah(Pelanggan p) {
        String sql = "INSERT INTO pelanggan (kode_pelanggan, nama_pelanggan, alamat, no_telepon) VALUES (?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getKodePelanggan());
            ps.setString(2, p.getNama());
            ps.setString(3, p.getAlamat());
            ps.setString(4, p.getNoTelepon());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal menambah pelanggan: " + e.getMessage(), e);
        }
    }

    public void update(Pelanggan p) {
        String sql = "UPDATE pelanggan SET kode_pelanggan=?, nama_pelanggan=?, alamat=?, no_telepon=? WHERE id_pelanggan=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getKodePelanggan());
            ps.setString(2, p.getNama());
            ps.setString(3, p.getAlamat());
            ps.setString(4, p.getNoTelepon());
            ps.setInt(5, p.getIdPelanggan());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mengupdate pelanggan: " + e.getMessage(), e);
        }
    }

    public void hapus(int idPelanggan) {
        String sql = "DELETE FROM pelanggan WHERE id_pelanggan=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPelanggan);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal menghapus pelanggan: " + e.getMessage(), e);
        }
    }

    private Pelanggan mapRow(ResultSet rs) throws SQLException {
        return new Pelanggan(rs.getInt("id_pelanggan"), rs.getString("kode_pelanggan"),
                rs.getString("nama_pelanggan"), rs.getString("alamat"), rs.getString("no_telepon"));
    }
}
