package app.model;

/**
 * Kelas Pelanggan merepresentasikan data pelanggan.
 * Inheritance: Pelanggan extends Person.
 */
public class Pelanggan extends Person {
    private int idPelanggan;
    private String kodePelanggan;
    private String alamat;
    private String noTelepon;

    public Pelanggan(String nama) {
        super(nama);
    }

    public Pelanggan(int idPelanggan, String kodePelanggan, String nama, String alamat, String noTelepon) {
        super(nama);
        this.idPelanggan = idPelanggan;
        this.kodePelanggan = kodePelanggan;
        this.alamat = alamat;
        this.noTelepon = noTelepon;
    }

    public int getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(int idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public String getKodePelanggan() {
        return kodePelanggan;
    }

    public void setKodePelanggan(String kodePelanggan) {
        this.kodePelanggan = kodePelanggan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    // Polimorfisme: override method abstrak dari Person dengan implementasi berbeda
    @Override
    public String getDeskripsi() {
        return "Pelanggan: " + getNama() + " (" + kodePelanggan + ")";
    }

    @Override
    public String toString() {
        return kodePelanggan + " - " + getNama();
    }
}
