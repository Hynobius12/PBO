package app.view;

import app.dao.PelangganDAO;
import app.model.Pelanggan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Panel untuk CRUD dan pencarian Data Pelanggan.
 */
public class PelangganPanel extends JPanel {

    private final PelangganDAO pelangganDAO = new PelangganDAO();
    private DefaultTableModel model;
    private JTable table;
    private JTextField txtKode, txtNama, txtAlamat, txtTelp, txtCari;

    public PelangganPanel() {
        setLayout(new BorderLayout(5, 5));

        JPanel form = new JPanel(new GridLayout(2, 4, 5, 5));
        txtKode = new JTextField();
        txtNama = new JTextField();
        txtAlamat = new JTextField();
        txtTelp = new JTextField();
        form.add(new JLabel("Kode Pelanggan:"));
        form.add(txtKode);
        form.add(new JLabel("Nama:"));
        form.add(txtNama);
        form.add(new JLabel("Alamat:"));
        form.add(txtAlamat);
        form.add(new JLabel("No. Telepon:"));
        form.add(txtTelp);

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

        JPanel searchPanel = new JPanel(new BorderLayout(5, 5));
        txtCari = new JTextField();
        JButton btnCari = new JButton("Cari");
        JButton btnReset = new JButton("Tampilkan Semua");
        JPanel searchBtns = new JPanel();
        searchBtns.add(btnCari);
        searchBtns.add(btnReset);
        searchPanel.add(new JLabel("Cari (kode/nama): "), BorderLayout.WEST);
        searchPanel.add(txtCari, BorderLayout.CENTER);
        searchPanel.add(searchBtns, BorderLayout.EAST);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(formWrapper, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        model = new DefaultTableModel(new Object[]{"ID", "Kode", "Nama", "Alamat", "No. Telepon"}, 0) {
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
        btnCari.addActionListener(e -> cari());
        btnReset.addActionListener(e -> loadData());
        table.getSelectionModel().addListSelectionListener(e -> isiFormDariTabel());

        loadData();
    }

    private void loadData() {
        model.setRowCount(0);
        List<Pelanggan> list = pelangganDAO.getAll();
        for (Pelanggan p : list) {
            model.addRow(new Object[]{p.getIdPelanggan(), p.getKodePelanggan(), p.getNama(), p.getAlamat(), p.getNoTelepon()});
        }
    }

    private void cari() {
        String keyword = txtCari.getText().trim();
        model.setRowCount(0);
        List<Pelanggan> list = pelangganDAO.cari(keyword);
        for (Pelanggan p : list) {
            model.addRow(new Object[]{p.getIdPelanggan(), p.getKodePelanggan(), p.getNama(), p.getAlamat(), p.getNoTelepon()});
        }
    }

    private void isiFormDariTabel() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            txtKode.setText(model.getValueAt(row, 1).toString());
            txtNama.setText(model.getValueAt(row, 2).toString());
            txtAlamat.setText(String.valueOf(model.getValueAt(row, 3)));
            txtTelp.setText(String.valueOf(model.getValueAt(row, 4)));
        }
    }

    private void bersihkanForm() {
        txtKode.setText("");
        txtNama.setText("");
        txtAlamat.setText("");
        txtTelp.setText("");
        table.clearSelection();
    }

    private void tambah() {
        try {
            Pelanggan p = ambilInput();
            pelangganDAO.tambah(p);
            loadData();
            bersihkanForm();
            JOptionPane.showMessageDialog(this, "Data pelanggan berhasil ditambahkan.");
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
            Pelanggan p = ambilInput();
            p.setIdPelanggan((int) model.getValueAt(row, 0));
            pelangganDAO.update(p);
            loadData();
            bersihkanForm();
            JOptionPane.showMessageDialog(this, "Data pelanggan berhasil diupdate.");
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
                pelangganDAO.hapus(id);
                loadData();
                bersihkanForm();
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private Pelanggan ambilInput() {
        if (txtKode.getText().trim().isEmpty() || txtNama.getText().trim().isEmpty()) {
            throw new RuntimeException("Kode dan nama pelanggan wajib diisi.");
        }
        return new Pelanggan(0, txtKode.getText().trim(), txtNama.getText().trim(),
                txtAlamat.getText().trim(), txtTelp.getText().trim());
    }
}
