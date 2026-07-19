package model;

public class Reservasi {
    private int idReservasi;
    private int idPelanggan;
    private int idLapangan;
    private String tanggal;
    private String jam;

    public Reservasi(int idReservasi, int idPelanggan, int idLapangan, String tanggal, String jam) {
        this.idReservasi = idReservasi;
        this.idPelanggan = idPelanggan;
        this.idLapangan = idLapangan;
        this.tanggal = tanggal;
        this.jam = jam;
    }

    public int getIdReservasi() { return idReservasi; }
    public int getIdPelanggan() { return idPelanggan; }
    public int getIdLapangan() { return idLapangan; }
    public String getTanggal() { return tanggal; }
    public String getJam() { return jam; }
}