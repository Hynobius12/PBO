package app.view;

import app.dao.BarangDAO;
import app.dao.PelangganDAO;
import app.dao.TransaksiDAO;
import app.exception.StokTidakCukupException;
import app.model.Barang;
import app.model.DetailTransaksi;
import app.model.Pelanggan;
import app.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel Transaksi Penjualan (keranjang belanja).
 * Menampilkan pemilihan pelanggan, barang, jumlah, lalu menyimpan
 * transaksi ke database melalui TransaksiDAO (stored procedure).
 */
public class TransaksiPanel extends JPanel {

    private final User user;
    private final BarangDAO barangDAO = new BarangDAO();
    private final PelangganDAO pelangganDAO = new PelangganDAO();
    private final TransaksiDAO transaksiDAO = new TransaksiDAO();

    private JComboBox<Pelanggan> cmbPelanggan;
    private JComboBox<Barang> cmbBarang;
    private JTextField txtJumlah;
    private DefaultTableModel modelKeranjang;
    private JTable tableKeranjang;
    private JLabel lblTotal;

    private final List<DetailTransaksi> keranjang = new ArrayList<>();

    public TransaksiPanel(User user) {
        this.user = user;
        setLayout(new BorderLayout(5, 5));

        // ---- Panel atas: pilih pelanggan & barang ----
        JPanel topPanel = new JPanel(new GridLayout(3, 3, 5, 5));
        cmbPelanggan = new JComboBox<>();
        cmbBarang = new JComboBox<>();
        txtJumlah = new JTextField();

        topPanel.add(new JLabel("Pelanggan:"));
        topPanel.add(cmbPelanggan);
        topPanel.add(new JLabel());

        topPanel.add(new JLabel("Barang:"));
        topPanel.add(cmbBarang);
        topPanel.add(new JLabel());

        topPanel.add(new JLabel("Jumlah:"));
        topPanel.add(txtJumlah);
        JButton btnTambahKeranjang = new JButton("Tambah ke Keranjang");
        topPanel.add(btnTambahKeranjang);

        add(topPanel, BorderLayout.NORTH);

        // ---- Tabel keranjang ----
        modelKeranjang = new DefaultTableModel(new Object[]{"Kode", "Nama Barang", "Harga", "Jumlah", "Subtotal"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableKeranjang = new JTable(modelKeranjang);
        add(new JScrollPane(tableKeranjang), BorderLayout.CENTER);

        // ---- Panel bawah: total & tombol simpan ----
        JPanel bottomPanel = new JPanel(new BorderLayout());
        lblTotal = new JLabel("Total: Rp 0", SwingConstants.RIGHT);
        lblTotal.setFont(new Font("SansSerif", Font.BOLD, 14));
        bottomPanel.add(lblTotal, BorderLayout.NORTH);

        JPanel btnBottom = new JPanel();
        JButton btnHapusItem = new JButton("Hapus Item Terpilih");
        JButton btnSimpan = new JButton("Simpan Transaksi");
        JButton btnBaru = new JButton("Transaksi Baru");
        btnBottom.add(btnHapusItem);
        btnBottom.add(btnSimpan);
        btnBottom.add(btnBaru);
        bottomPanel.add(btnBottom, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        // ---- Events ----
        btnTambahKeranjang.addActionListener(e -> tambahKeKeranjang());
        btnHapusItem.addActionListener(e -> hapusItemKeranjang());
        btnSimpan.addActionListener(e -> simpanTransaksi());
        btnBaru.addActionListener(e -> transaksiBaru());

        loadCombo();
    }

    private void loadCombo() {
        cmbPelanggan.removeAllItems();
        for (Pelanggan p : pelangganDAO.getAll()) {
            cmbPelanggan.addItem(p);
        }
        cmbBarang.removeAllItems();
        for (Barang b : barangDAO.getAll()) {
            cmbBarang.addItem(b);
        }
    }

    private void tambahKeKeranjang() {
        Barang barangDipilih = (Barang) cmbBarang.getSelectedItem();
        if (barangDipilih == null) {
            JOptionPane.showMessageDialog(this, "Data barang belum tersedia.");
            return;
        }
        try {
            int jumlah = Integer.parseInt(txtJumlah.getText().trim());
            if (jumlah <= 0) {
                throw new NumberFormatException();
            }
            if (jumlah > barangDipilih.getStok()) {
                JOptionPane.showMessageDialog(this,
                        "Stok tidak mencukupi. Stok tersedia: " + barangDipilih.getStok(),
                        "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            DetailTransaksi item = new DetailTransaksi(barangDipilih, jumlah);
            keranjang.add(item);

            modelKeranjang.addRow(new Object[]{
                    barangDipilih.getKodeBarang(), barangDipilih.getNamaBarang(),
                    barangDipilih.getHarga(), jumlah, item.getSubtotal()
            });

            hitungTotal();
            txtJumlah.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Jumlah harus berupa angka lebih dari 0.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void hapusItemKeranjang() {
        int row = tableKeranjang.getSelectedRow();
        if (row >= 0) {
            keranjang.remove(row);
            modelKeranjang.removeRow(row);
            hitungTotal();
        }
    }

    private void hitungTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (DetailTransaksi d : keranjang) {
            total = total.add(d.getSubtotal());
        }
        lblTotal.setText("Total: Rp " + total);
    }

    private void simpanTransaksi() {
        if (keranjang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Keranjang masih kosong.");
            return;
        }
        Pelanggan pelanggan = (Pelanggan) cmbPelanggan.getSelectedItem();
        if (pelanggan == null) {
            JOptionPane.showMessageDialog(this, "Pilih pelanggan terlebih dahulu.");
            return;
        }

        String noTransaksi = "TRX" + System.currentTimeMillis();
        try {
            transaksiDAO.simpanTransaksi(noTransaksi, pelanggan.getIdPelanggan(), user.getIdUser(), keranjang);
            JOptionPane.showMessageDialog(this, "Transaksi berhasil disimpan dengan nomor: " + noTransaksi);
            transaksiBaru();
            loadCombo(); // refresh stok barang setelah transaksi
        } catch (StokTidakCukupException ex) {
            JOptionPane.showMessageDialog(this, "Transaksi gagal: " + ex.getMessage(), "Stok Tidak Cukup", JOptionPane.ERROR_MESSAGE);
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void transaksiBaru() {
        keranjang.clear();
        modelKeranjang.setRowCount(0);
        hitungTotal();
        txtJumlah.setText("");
    }
}
