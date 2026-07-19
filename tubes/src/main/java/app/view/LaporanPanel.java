package app.view;

import app.dao.TransaksiDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Panel Laporan Penjualan.
 * Data diambil dari VIEW v_laporan_penjualan di database.
 */
public class LaporanPanel extends JPanel {

    private final TransaksiDAO transaksiDAO = new TransaksiDAO();
    private DefaultTableModel model;
    private JTable table;

    public LaporanPanel() {
        setLayout(new BorderLayout(5, 5));

        JButton btnRefresh = new JButton("Muat Ulang Laporan");
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(btnRefresh);
        add(top, BorderLayout.NORTH);

        model = new DefaultTableModel(new Object[]{
                "No Transaksi", "Tanggal", "Pelanggan", "Kasir", "Barang", "Jumlah", "Subtotal", "Total Transaksi"
        }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        btnRefresh.addActionListener(e -> loadData());
        loadData();
    }

    private void loadData() {
        model.setRowCount(0);
        List<String[]> laporan = transaksiDAO.getLaporan();
        for (String[] row : laporan) {
            model.addRow(row);
        }
    }
}
