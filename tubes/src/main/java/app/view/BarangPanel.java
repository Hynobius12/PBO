package app.view;

import app.dao.BarangDAO;
import app.model.Barang;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Panel untuk CRUD dan pencarian Data Barang.
 */
public class BarangPanel extends JPanel {

    private final BarangDAO barangDAO = new BarangDAO();
    private DefaultTableModel model;
    private JTable table;
    private JTextField txtKode, txtNama, txtHarga, txtStok, txtCari;

    public BarangPanel() {
        setLayout(new BorderLayout(5, 5));

        // ---- Form input ----
        JPanel form = new JPanel(new GridLayout(2, 4, 5, 5));
        txtKode = new JTextField();
        txtNama = new JTextField();
        txtHarga = new JTextField();
        txtStok = new JTextField();
        form.add(new JLabel("Kode Barang:"));
        form.add(txtKode);
        form.add(new JLabel("Nama Barang:"));
        form.add(txtNama);
        form.add(new JLabel("Harga:"));
        form.add(txtHarga);
        form.add(new JLabel("Stok:"));
        form.add(txtStok);

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

        // ---- Pencarian ----
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

        // ---- Tabel ----
        model = new DefaultTableModel(new Object[]{"ID", "Kode", "Nama Barang", "Harga", "Stok"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ---- Events ----
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
        List<Barang> list = barangDAO.getAll();
        for (Barang b : list) {
            model.addRow(new Object[]{b.getIdBarang(), b.getKodeBarang(), b.getNamaBarang(), b.getHarga(), b.getStok()});
        }
    }

    private void cari() {
        String keyword = txtCari.getText().trim();
        model.setRowCount(0);
        List<Barang> list = barangDAO.cari(keyword);
        for (Barang b : list) {
            model.addRow(new Object[]{b.getIdBarang(), b.getKodeBarang(), b.getNamaBarang(), b.getHarga(), b.getStok()});
        }
    }

    private void isiFormDariTabel() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            txtKode.setText(model.getValueAt(row, 1).toString());
            txtNama.setText(model.getValueAt(row, 2).toString());
            txtHarga.setText(model.getValueAt(row, 3).toString());
            txtStok.setText(model.getValueAt(row, 4).toString());
        }
    }

    private void bersihkanForm() {
        txtKode.setText("");
        txtNama.setText("");
        txtHarga.setText("");
        txtStok.setText("");
        table.clearSelection();
    }

    private void tambah() {
        try {
            Barang b = ambilInput();
            barangDAO.tambah(b);
            loadData();
            bersihkanForm();
            JOptionPane.showMessageDialog(this, "Data barang berhasil ditambahkan.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Harga dan stok harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
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
            Barang b = ambilInput();
            b.setIdBarang((int) model.getValueAt(row, 0));
            barangDAO.update(b);
            loadData();
            bersihkanForm();
            JOptionPane.showMessageDialog(this, "Data barang berhasil diupdate.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Harga dan stok harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
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
                barangDAO.hapus(id);
                loadData();
                bersihkanForm();
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private Barang ambilInput() {
        if (txtKode.getText().trim().isEmpty() || txtNama.getText().trim().isEmpty()) {
            throw new RuntimeException("Kode dan nama barang wajib diisi.");
        }
        Barang b = new Barang();
        b.setKodeBarang(txtKode.getText().trim());
        b.setNamaBarang(txtNama.getText().trim());
        b.setHarga(new BigDecimal(txtHarga.getText().trim()));
        b.setStok(Integer.parseInt(txtStok.getText().trim()));
        return b;
    }
}
