package app.view;

import app.dao.UserDAO;
import app.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Panel untuk CRUD Data User.
 */
public class UserPanel extends JPanel {

    private final UserDAO userDAO = new UserDAO();
    private DefaultTableModel model;
    private JTable table;
    private JTextField txtUsername, txtPassword, txtNama;
    private JComboBox<String> cmbRole;

    public UserPanel() {
        setLayout(new BorderLayout(5, 5));

        JPanel form = new JPanel(new GridLayout(2, 4, 5, 5));
        txtUsername = new JTextField();
        txtPassword = new JTextField();
        txtNama = new JTextField();
        cmbRole = new JComboBox<>(new String[]{"admin", "kasir"});
        form.add(new JLabel("Username:"));
        form.add(txtUsername);
        form.add(new JLabel("Password:"));
        form.add(txtPassword);
        form.add(new JLabel("Nama Lengkap:"));
        form.add(txtNama);
        form.add(new JLabel("Role:"));
        form.add(cmbRole);

        JPanel formWrapper = new JPanel(new BorderLayout());
        formWrapper.add(form, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton btnTambah = new JButton("Tambah");
        JButton btnUpdate = new JButton("Update");
        JButton btnHapus = new JButton("Hapus");
        JButton btnBersih = new JButton("Bersihkan");
        btnPanel.add(btnTambah);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnHapus);
        btnPanel.add(btnBersih);
        formWrapper.add(btnPanel, BorderLayout.SOUTH);

        add(formWrapper, BorderLayout.NORTH);

        model = new DefaultTableModel(new Object[]{"ID", "Username", "Password", "Nama Lengkap", "Role"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        btnTambah.addActionListener(e -> tambah());
        btnUpdate.addActionListener(e -> update());
        btnHapus.addActionListener(e -> hapus());
        btnBersih.addActionListener(e -> bersihkanForm());
        table.getSelectionModel().addListSelectionListener(e -> isiFormDariTabel());

        loadData();
    }

    private void loadData() {
        model.setRowCount(0);
        List<User> list = userDAO.getAll();
        for (User u : list) {
            model.addRow(new Object[]{u.getIdUser(), u.getUsername(), u.getPassword(), u.getNama(), u.getRole()});
        }
    }

    private void isiFormDariTabel() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            txtUsername.setText(model.getValueAt(row, 1).toString());
            txtPassword.setText(model.getValueAt(row, 2).toString());
            txtNama.setText(model.getValueAt(row, 3).toString());
            cmbRole.setSelectedItem(model.getValueAt(row, 4).toString());
        }
    }

    private void bersihkanForm() {
        txtUsername.setText("");
        txtPassword.setText("");
        txtNama.setText("");
        cmbRole.setSelectedIndex(0);
        table.clearSelection();
    }

    private void tambah() {
        try {
            User u = ambilInput();
            userDAO.tambah(u);
            loadData();
            bersihkanForm();
            JOptionPane.showMessageDialog(this, "Data user berhasil ditambahkan.");
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void update() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin diupdate terlebih dahulu.");
            return;
        }
        try {
            User u = ambilInput();
            u.setIdUser((int) model.getValueAt(row, 0));
            userDAO.update(u);
            loadData();
            bersihkanForm();
            JOptionPane.showMessageDialog(this, "Data user berhasil diupdate.");
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void hapus() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus terlebih dahulu.");
            return;
        }
        int konfirmasi = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (konfirmasi == JOptionPane.YES_OPTION) {
            try {
                int id = (int) model.getValueAt(row, 0);
                userDAO.hapus(id);
                loadData();
                bersihkanForm();
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private User ambilInput() {
        if (txtUsername.getText().trim().isEmpty() || txtPassword.getText().trim().isEmpty()) {
            throw new RuntimeException("Username dan password wajib diisi.");
        }
        return new User(0, txtUsername.getText().trim(), txtPassword.getText().trim(),
                txtNama.getText().trim(), (String) cmbRole.getSelectedItem());
    }
}
