package app.exception;

/**
 * Exception khusus (custom exception) yang dilempar ketika
 * stok barang tidak mencukupi untuk sebuah transaksi penjualan.
 */
public class StokTidakCukupException extends Exception {
    public StokTidakCukupException(String message) {
        super(message);
    }
}
