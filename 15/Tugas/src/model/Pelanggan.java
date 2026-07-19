package model;

public class Pelanggan extends User {
    private String nomorTelepon;

    public Pelanggan(int id, String nama, String nomorTelepon) {
        super(id, nama);
        this.nomorTelepon = nomorTelepon;
    }

    public String getNomorTelepon() { return nomorTelepon; }
    public void setNomorTelepon(String nomorTelepon) { this.nomorTelepon = nomorTelepon; }

    @Override
    public void tampilInfo() {
        System.out.println("ID Pelanggan : " + getId());
        System.out.println("Nama         : " + getNama());
        System.out.println("No. Telepon  : " + nomorTelepon);
        System.out.println("-----------------------------------");
    }
}