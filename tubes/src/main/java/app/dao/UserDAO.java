package app.dao;

import app.exception.DataTidakDitemukanException;
import app.model.User;
import app.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object untuk tabel user.
 * Menangani proses login dan CRUD data user.
 */
public class UserDAO {

    public User login(String username, String password) throws DataTidakDitemukanException {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getInt("id_user"), rs.getString("username"),
                            rs.getString("password"), rs.getString("nama_lengkap"), rs.getString("role"));
                } else {
                    throw new DataTidakDitemukanException("Username atau password salah.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal login: " + e.getMessage(), e);
        }
    }

    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM user ORDER BY id_user";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new User(rs.getInt("id_user"), rs.getString("username"),
                        rs.getString("password"), rs.getString("nama_lengkap"), rs.getString("role")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mengambil data user: " + e.getMessage(), e);
        }
        return list;
    }

    public void tambah(User user) {
        String sql = "INSERT INTO user (username, password, nama_lengkap, role) VALUES (?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getNama());
            ps.setString(4, user.getRole());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal menambah user: " + e.getMessage(), e);
        }
    }

    public void update(User user) {
        String sql = "UPDATE user SET username=?, password=?, nama_lengkap=?, role=? WHERE id_user=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getNama());
            ps.setString(4, user.getRole());
            ps.setInt(5, user.getIdUser());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mengupdate user: " + e.getMessage(), e);
        }
    }

    public void hapus(int idUser) {
        String sql = "DELETE FROM user WHERE id_user=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUser);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal menghapus user: " + e.getMessage(), e);
        }
    }
}
