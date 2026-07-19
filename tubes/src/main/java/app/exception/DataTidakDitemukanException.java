package app.exception;

/**
 * Custom exception yang dilempar ketika data yang dicari
 * (barang, pelanggan, user, dll) tidak ditemukan di database.
 */
public class DataTidakDitemukanException extends Exception {
    public DataTidakDitemukanException(String message) {
        super(message);
    }
}
