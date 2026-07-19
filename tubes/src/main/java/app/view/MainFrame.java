package app.view;

import app.model.User;

import javax.swing.*;
import java.awt.*;

/**
 * Frame utama aplikasi setelah login berhasil.
 * Berisi tab-tab untuk setiap fitur (Barang, Pelanggan, User, Transaksi, Laporan).
 */
public class MainFrame extends JFrame {

    public MainFrame(User user) {
        setTitle("Aplikasi Penjualan - Login sebagai: " + user.getDeskripsi());
        setSize(950, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Data Barang", new BarangPanel());
        tabbedPane.addTab("Data Pelanggan", new PelangganPanel());
        tabbedPane.addTab("Data User", new UserPanel());
        tabbedPane.addTab("Transaksi Penjualan", new TransaksiPanel(user));
        tabbedPane.addTab("Laporan Penjualan", new LaporanPanel());

        add(tabbedPane, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
        bottom.add(btnLogout);
        add(bottom, BorderLayout.SOUTH);
    }
}
