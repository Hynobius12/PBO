package app.dao;

import app.model.Barang;
import app.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object untuk tabel barang.
 * Menangani CRUD dan pencarian data barang.
 */
public class BarangDAO {

    public List<Barang> getAll() {
        List<Barang> list = new ArrayList<>();
        String sql = "SELECT * FROM barang ORDER BY id_barang";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mengambil data barang: " + e.getMessage(), e);
        }
        return list;
    }

    public List<Barang> cari(String keyword) {
        List<Barang> list = new ArrayList<>();
        String sql = "SELECT * FROM barang WHERE kode_barang LIKE ? OR nama_barang LIKE ? ORDER BY id_barang";
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
            throw new RuntimeException("Gagal mencari data barang: " + e.getMessage(), e);
        }
        return list;
    }

    public void tambah(Barang b) {
        String sql = "INSERT INTO barang (kode_barang, nama_barang, harga, stok) VALUES (?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, b.getKodeBarang());
            ps.setString(2, b.getNamaBarang());
            ps.setBigDecimal(3, b.getHarga());
            ps.setInt(4, b.getStok());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal menambah barang: " + e.getMessage(), e);
        }
    }

    public void update(Barang b) {
        String sql = "UPDATE barang SET kode_barang=?, nama_barang=?, harga=?, stok=? WHERE id_barang=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, b.getKodeBarang());
            ps.setString(2, b.getNamaBarang());
            ps.setBigDecimal(3, b.getHarga());
            ps.setInt(4, b.getStok());
            ps.setInt(5, b.getIdBarang());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mengupdate barang: " + e.getMessage(), e);
        }
    }

    public void hapus(int idBarang) {
        String sql = "DELETE FROM barang WHERE id_barang=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idBarang);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal menghapus barang: " + e.getMessage(), e);
        }
    }

    private Barang mapRow(ResultSet rs) throws SQLException {
        return new Barang(rs.getInt("id_barang"), rs.getString("kode_barang"),
                rs.getString("nama_barang"), rs.getBigDecimal("harga"), rs.getInt("stok"));
    }
}
