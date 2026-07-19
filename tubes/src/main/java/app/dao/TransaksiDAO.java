package app.dao;

import app.exception.StokTidakCukupException;
import app.model.DetailTransaksi;
import app.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object untuk transaksi penjualan.
 * Menggunakan Stored Procedure (sp_tambah_detail_transaksi) untuk menyimpan
 * setiap item transaksi, dan View (v_laporan_penjualan) untuk laporan.
 */
public class TransaksiDAO {

    /**
     * Menyimpan transaksi baru beserta seluruh item di keranjang.
     * Setiap item disimpan melalui stored procedure yang otomatis:
     * - menghitung subtotal (memakai FUNCTION fn_hitung_subtotal)
     * - mengurangi stok barang (lewat TRIGGER trg_kurangi_stok)
     * - mengupdate total transaksi
     */
    public void simpanTransaksi(String noTransaksi, int idPelanggan, int idUser, List<DetailTransaksi> keranjang)
            throws StokTidakCukupException {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            // 1. Insert header transaksi
            String sqlHeader = "INSERT INTO transaksi (no_transaksi, id_pelanggan, id_user, total) VALUES (?,?,?,0)";
            int idTransaksi;
            try (PreparedStatement ps = conn.prepareStatement(sqlHeader, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, noTransaksi);
                ps.setInt(2, idPelanggan);
                ps.setInt(3, idUser);
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    keys.next();
                    idTransaksi = keys.getInt(1);
                }
            }

            // 2. Panggil stored procedure untuk setiap item di keranjang
            try (CallableStatement cs = conn.prepareCall("{call sp_tambah_detail_transaksi(?,?,?)}")) {
                for (DetailTransaksi item : keranjang) {
                    cs.setInt(1, idTransaksi);
                    cs.setInt(2, item.getBarang().getIdBarang());
                    cs.setInt(3, item.getJumlah());
                    cs.execute();
                }
            }

            conn.commit();
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                // abaikan error saat rollback
            }
            // Error dari SIGNAL SQLSTATE '45000' di stored procedure (stok tidak cukup)
            if (e.getSQLState() != null && e.getSQLState().equals("45000")) {
                throw new StokTidakCukupException(e.getMessage());
            }
            throw new RuntimeException("Gagal menyimpan transaksi: " + e.getMessage(), e);
        } finally {
            try {
                if (conn != null) conn.setAutoCommit(true);
            } catch (SQLException ex) {
                // abaikan
            }
        }
    }

    /**
     * Mengambil data laporan penjualan dari VIEW v_laporan_penjualan.
     */
    public List<String[]> getLaporan() {
        List<String[]> list = new ArrayList<>();
        String sql = "SELECT * FROM v_laporan_penjualan";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new String[]{
                        rs.getString("no_transaksi"),
                        rs.getString("tanggal"),
                        rs.getString("nama_pelanggan"),
                        rs.getString("kasir"),
                        rs.getString("nama_barang"),
                        rs.getString("jumlah"),
                        rs.getString("subtotal"),
                        rs.getString("total_transaksi")
                });
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mengambil laporan penjualan: " + e.getMessage(), e);
        }
        return list;
    }
}
