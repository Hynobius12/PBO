package app.model;

import java.math.BigDecimal;

/**
 * Kelas DetailTransaksi merepresentasikan satu baris item
 * di dalam sebuah transaksi (keranjang belanja).
 */
public class DetailTransaksi {
    private Barang barang;
    private int jumlah;
    private BigDecimal subtotal;

    public DetailTransaksi(Barang barang, int jumlah) {
        this.barang = barang;
        this.jumlah = jumlah;
        this.subtotal = barang.getHarga().multiply(BigDecimal.valueOf(jumlah));
    }

    public Barang getBarang() {
        return barang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }
}
