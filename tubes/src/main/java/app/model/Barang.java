package app.model;

import java.math.BigDecimal;

/**
 * Kelas Barang merepresentasikan data barang/produk.
 * Enkapsulasi: semua atribut private, diakses lewat getter/setter.
 */
public class Barang {
    private int idBarang;
    private String kodeBarang;
    private String namaBarang;
    private BigDecimal harga;
    private int stok;

    public Barang() {
    }

    public Barang(int idBarang, String kodeBarang, String namaBarang, BigDecimal harga, int stok) {
        this.idBarang = idBarang;
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.harga = harga;
        this.stok = stok;
    }

    public int getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    @Override
    public String toString() {
        return kodeBarang + " - " + namaBarang;
    }
}
