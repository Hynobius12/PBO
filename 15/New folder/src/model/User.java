package model;

public class User {
    private int id;
    private String nama;

    public User(int id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public void tampilInfo() {
        System.out.println("ID   : " + id);
        System.out.println("Nama : " + nama);
    }
}