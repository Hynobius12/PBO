package model;

public class Lapangan {
    private int idLapangan;
    private String namaLapangan;
    private String jenisLapangan;
    private String status;

    public Lapangan(int idLapangan, String namaLapangan, String jenisLapangan, String status) {
        this.idLapangan = idLapangan;
        this.namaLapangan = namaLapangan;
        this.jenisLapangan = jenisLapangan;
        this.status = status;
    }

    public int getIdLapangan() { return idLapangan; }
    public void setIdLapangan(int idLapangan) { this.idLapangan = idLapangan; }

    public String getNamaLapangan() { return namaLapangan; }
    public void setNamaLapangan(String namaLapangan) { this.namaLapangan = namaLapangan; }

    public String getJenisLapangan() { return jenisLapangan; }
    public void setJenisLapangan(String jenisLapangan) { this.jenisLapangan = jenisLapangan; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}